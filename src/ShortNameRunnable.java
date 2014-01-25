import java.util.concurrent.ConcurrentHashMap;

import com.infoarmy.ir.model.company.Company;
import com.infoarmy.ir.model.company.CompanyNameUtils;

/**
 * 
 */

/**
 * @author Gautam
 * 
 */
public class ShortNameRunnable implements Runnable {
	Company currentCompany, nextCompany;

	public void run() {
		if (currentCompany.getShortName() != null
				&& nextCompany.getShortName() != null) {

			int companyScore = calcualateStringDistance(
					CompanyNameUtils.getStrippedName(currentCompany
							.getShortName()),
					CompanyNameUtils.getStrippedName(nextCompany.getShortName()));
			// System.out.println(currentCompany.getId()+","+nextCompany.getId()+"=="+"SHORT NAME is"+companyScore);
			if (sample2.haspMap.get(currentCompany.getId() + ","
					+ nextCompany.getId()) != null) {
				ConcurrentHashMap<String, String> value = sample2.haspMap
						.get(currentCompany.getId() + "," + nextCompany.getId());
				value.put("SHORT_NAME", String.valueOf(companyScore));
			} else {
				ConcurrentHashMap<String, String> value = new ConcurrentHashMap<String, String>(
						10);
				value.put("SHORT_NAME", String.valueOf(companyScore));
				sample2.haspMap.put(
						currentCompany.getId() + "," + nextCompany.getId(),
						value);
			}
		}
	}

	private int calcualateStringDistance(String strippedName,
			String strippedName2) {
		return LevenshteinDistance.printDistance(strippedName, strippedName2);
	}

	public ShortNameRunnable(Company currentCompany, Company nextCompany) {
		this.currentCompany = currentCompany;
		this.nextCompany = nextCompany;
	}

}
