import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.infoarmy.ir.model.company.Company;

public class sample2 {
	static ConcurrentHashMap<String, ConcurrentHashMap<String, String>> haspMap;
	static FileWriter fw;
	static BufferedWriter bw;
	static File file2 = new File(
			"/Users/gautambalasubramanian/companies_new_1000duplicates.txt");
	static int totalSize = 10000;
	static int batchCount = 100;
	static int startIndex = 0;
	static int offset = batchCount;
	static List<Company> allCompaniesList;
	static ExecutorService executorService;

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {
		allCompaniesList = readContentsFromFile();
//		totalSize = allCompaniesList.size();
		fw = new FileWriter(file2.getAbsoluteFile(), true);
		if (!file2.exists()) {
			file2.createNewFile();
		}

		while (totalSize != offset) {
			haspMap = null;
			haspMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>(batchCount);
			if (totalSize < offset) {
				offset = totalSize;
			}
			executorService = null;
			executorService = Executors.newFixedThreadPool(100);
			for (int i = startIndex; i < offset; i++) {
				for (int j = 0; j < i; j++) {
					System.out.println(j + "," + i);
					Company currentCompany = allCompaniesList.get(i);
					Company nextCompany = allCompaniesList.get(j);

					ShortNameRunnable shortNameRunnable = new ShortNameRunnable(
							currentCompany, nextCompany);
					CikNumberRunnable cikNumberRunnable = new CikNumberRunnable(
							currentCompany, nextCompany);
					PrimaryNameRunnable primaryNameRunnable = new PrimaryNameRunnable(
							currentCompany, nextCompany);
					PrimaryDomainNameRunnable primaryDomainNameRunnable = new PrimaryDomainNameRunnable(
							currentCompany, nextCompany);

					executorService.execute(primaryNameRunnable);
					executorService.execute(shortNameRunnable);
					executorService.execute(cikNumberRunnable);
					executorService.execute(primaryDomainNameRunnable);
				}
			}
			executorService.shutdown();
			
			while (true) {
				if (executorService.isTerminated()) {
					break;
				}
			}
			
			System.out.println("Computed HaspMap");
			printMap(haspMap);
			
			if (offset != totalSize) {
				startIndex = offset;
				offset = offset + batchCount;
				
			}
		}

	}

	public static void printMap(Map mp) throws IOException {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			ConcurrentHashMap<String, String> haspMap = new ConcurrentHashMap<String, String>();
			haspMap = (ConcurrentHashMap<String, String>) pairs.getValue();
			if (haspMap.get("CIK_NUMBER") != null) {
				int cikScore = Integer.parseInt((String) haspMap
						.get("CIK_NUMBER"));
				if (cikScore > 0) {
					displayCompany(haspMap);
				}
			}

			else if (haspMap.get("COMPANY_NAME") != null && haspMap.get("SHORT_NAME") != null) {
				int compScore = Integer.parseInt((String) haspMap
						.get("COMPANY_NAME"));
				int shortNameScore = Integer.parseInt((String) haspMap
						.get("SHORT_NAME"));
				if (compScore == 0 && shortNameScore == 0)
					displayCompany(haspMap);
			}
			else if (haspMap.get("PRIMARY_DOMAIN") != null && haspMap.get("SHORT_NAME") != null) {
				int compScore = Integer.parseInt((String) haspMap
						.get("PRIMARY_DOMAIN"));
				int shortNameScore = Integer.parseInt((String) haspMap
						.get("SHORT_NAME"));
				if (compScore == 0 && shortNameScore == 0)
					displayCompany(haspMap);
			}

		}
		it.remove(); // avoids a ConcurrentModificationException
	}

	private static List<Company> readContentsFromFile() throws IOException,
			ClassNotFoundException {
		InputStream file = new FileInputStream(
				"/Users/gautambalasubramanian/companies.ser");

		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = new ObjectInputStream(buffer);
		return (List<Company>) input.readObject();
	}

	private static void displayCompany(ConcurrentHashMap<String, String> haspMap) {

		PrintWriter out = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter("/Users/gautambalasubramanian/companies_new_1000duplicates.txt", true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.print("_______________________________" + "\n");
			out.print("COMPANY 1 ID == " + haspMap.get("COMPANY_1_COMPANY_ID")
					+ "\n");
			out.print("COMPANY 2 ID == " + haspMap.get("COMPANY_2_COMPANY_ID")
					+ "\n");
			out.print("COMPANY 1 COMPANY NAME == "
					+ haspMap.get("COMPANY_1_COMPANY_NAME") + "\n");
			out.print("COMPANY 2 COMPANY NAME == "
					+ haspMap.get("COMPANY_2_COMPANY_NAME") + "\n");

			out.print("COMPANY 1 SHORT NAME == "
					+ haspMap.get("COMPANY_1_SHORT_NAME") + "\n");
			out.print("COMPANY 2 SHORT NAME == "
					+ haspMap.get("COMPANY_2_SHORT_NAME") + "\n");

			out.print("COMPANY 1 CIK NUMBER == "
					+ haspMap.get("COMPANY_1_CIK_NUMBER") + "\n");
			out.print("COMPANY 2 CIK NUMBER == "
					+ haspMap.get("COMPANY_2_CIK_NUMBER") + "\n");
			out.print("_______________________________" + "\n");
		} catch (IOException e) {
			// File writing/opening failed at some stage.
		} finally {
			try {
				if (out != null) {
					out.close(); // Will close bw and fw too
				} else if (bw != null) {
					bw.close(); // Will close fw too
				} else if (fw != null) {
					fw.close();
				} else {
					// Oh boy did it fail hard! :3
				}
			} catch (IOException e) {
				// Closing the file writers failed for some obscure reason
			}
		}
	}

	private int calcualateStringDistance(String strippedName,
			String strippedName2) {
		if (strippedName == null || strippedName2 == null)
			return -1;
		return LevenshteinDistance.printDistance(strippedName, strippedName2);
	}
}
