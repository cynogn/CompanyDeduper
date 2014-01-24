import java.io.Serializable;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import com.infoarmy.ir.model.company.Company;
import com.infoarmy.ir.model.company.CompanyNameUtils;

/**
 * 
 */

/**
 * @author manojvivek
 *
 */
public class PrimaryNameRunnable implements Runnable {
	Company currentCompany, nextCompany;
	ConcurrentHashMap<HashSet<String>, Serializable> map;

	@Override
	public void run() {
		int companyScore = calcualateStringDistance(
				CompanyNameUtils.getStrippedName(currentCompany.getName()),
				CompanyNameUtils.getStrippedName(nextCompany.getName()));
		if (companyScore < 2) {
			displayCurrentAndNextCompany(currentCompany, nextCompany);
			System.out.println("_____________");

	}
	}

	private int calcualateStringDistance(String strippedName,
			String strippedName2) {
		return LevenshteinDistance.printDistance(strippedName, strippedName2);
	}

	public PrimaryNameRunnable(Company currentCompany, Company nextCompany,
			ConcurrentHashMap<HashSet<String>, Serializable> set) {
		this.currentCompany = currentCompany;
		this.nextCompany = nextCompany;
		this.map = map;
	}

}
