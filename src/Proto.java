import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.infoarmy.ir.model.company.Company;
import com.infoarmy.ir.model.company.CompanyNameUtils;

public class Proto {
	static File file = new File(
			"/Users/gautambalasubramanian/companies_new_result2.txt");
	static FileWriter fw;
	static BufferedWriter bw;
	static ConcurrentHashMap<HashSet<String>, Serializable> map;

	public static void main(String[] args) throws IOException {
		fw = new FileWriter(file.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		Thread.currentThread().setPriority(10);
		map = new ConcurrentHashMap<HashSet<String>, Serializable>();
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			InputStream file = new FileInputStream(
					"/Users/gautambalasubramanian/companies_new.ser");

			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			List<Company> comapniesList = (List<Company>) input.readObject();
			int size = comapniesList.size();
			for (int j = 0; j < size; j++) {
				Company currentCompany = comapniesList.get(j);
				System.out.println(currentCompany.getId() + "\t"
						+ currentCompany.getCik() + "\t"
						+ currentCompany.getShortName() + "\t"
						+ currentCompany.getPrimaryDomain());

			}
			System.out.println("COMAPANY NAME MATCHES:");
			System.out.println("**********************");
			bw.write("COMAPANY NAME MATCHES:\n");		// Company Name


			// Company Name
			for (int i = 0; i < (size - 1); i++) {

				for (int j = 1; j < i; j++) {
					Company currentCompany = comapniesList.get(i);
					Company nextCompany = comapniesList.get(j);
					PrimaryNameRunnable primaryNameRunnable = new PrimaryNameRunnable(
							currentCompany, nextCompany, map);
					executorService.execute(primaryNameRunnable);
				}
			}

			System.out.println("SHORT NAME MATCHES:\n");
			System.out.println("*******************");
			bw.write("SHORT NAME MATCHES:\n");		// Company Name
			for (int i = 0; i < (size - 1); i++) {
				for (int j = 1; j < i; j++) {
					Company currentCompany = comapniesList.get(i);
					Company nextCompany = comapniesList.get(j);
					int companyScore = calcualateStringDistance(
							CompanyNameUtils.getStrippedName(currentCompany
									.getShortName()),
							CompanyNameUtils.getStrippedName(nextCompany
									.getShortName()));
					if (companyScore < 1) {
						displayCurrentAndNextCompany(currentCompany,
								nextCompany);
						System.out.println("_____________");
					}
				}
			}

			// PRIMARY domain match
			System.out.println("COMAPANY PRIMARY DOMAIN NAME MATCHES:");
			System.out.println("*************************************");
			bw.write("COMAPANY PRIMARY DOMAIN NAME MATCHES:\n");

			for (int i = 0; i < (size - 1); i++) {
				for (int j = 1; j < i; j++) {
					Company currentCompany = comapniesList.get(i);
					Company nextCompany = comapniesList.get(j);
					int companyScore = calcualateStringDistance(
							CompanyNameUtils.getStrippedName(currentCompany
									.getPrimaryDomain()),
							CompanyNameUtils.getStrippedName(nextCompany
									.getPrimaryDomain()));
					if (companyScore < 2) {
						displayCurrentAndNextCompany(currentCompany,
								nextCompany);
						System.out.println("_____________");

					}
				}
			}

			// CIK Number match
			System.out.println("CIK NUMBER MATCHES:");
			System.out.println("*******************");
			bw.write("CIK NUMBER MATCHES:\n");

			for (int i = 0; i < size; i++) {
				for (int j = i + 1; j < size; j++) {
					Company currentCompany = comapniesList.get(i);
					Company nextCompany = comapniesList.get(j);
					if ((currentCompany.getCik() > 0) && (nextCompany.getCik() > 0)) {
						Boolean companyScore = currentCompany.getCik() == nextCompany
								.getCik();
						if (companyScore == true) {
							displayCurrentAndNextCompany(currentCompany,
									nextCompany);

						}
					}
				}
			}
			bw.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private static void displayCurrentAndNextCompany(Company currentCompany,
			Company nextCompany) {

		System.out.println(currentCompany.getId() + "\t"
				+ currentCompany.getCik() + "\t"
				+ currentCompany.getShortName() + "\t"
				+ currentCompany.getName() + "\t\t\t\t\t"
				+ currentCompany.getPrimaryDomain());
		System.out.println(nextCompany.getId() + "\t" + nextCompany.getCik()
				+ "\t" + nextCompany.getShortName() + "\t"
				+ nextCompany.getName() + "\t\t\t\t\t"
				+ nextCompany.getPrimaryDomain());

		try {

			String content = currentCompany.getId() + "\t"
					+ currentCompany.getCik() + "\t"
					+ currentCompany.getShortName() + "\t"
					+ currentCompany.getName() + "\t\t\t\t\t"
					+ currentCompany.getPrimaryDomain() + "\n"
					+ nextCompany.getId() + "\t" + nextCompany.getCik() + "\t"
					+ nextCompany.getShortName() + "\t" + nextCompany.getName()
					+ "\t\t\t\t\t" + nextCompany.getPrimaryDomain()+"\n";

			bw.write(content);
			bw.write("_________________\n");
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static int calcualateStringDistance(String name1, String name2) {
		return LevenshteinDistance.printDistance(name1, name2);
	}

}
