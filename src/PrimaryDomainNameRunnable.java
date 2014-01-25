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
public class PrimaryDomainNameRunnable implements Runnable {
	Company currentCompany, nextCompany;

	public void run() {
		if (currentCompany.getPrimaryDomain() != null
				&& nextCompany.getPrimaryDomain() != null) {
			int companyScore = calcualateStringDistance(
					CompanyNameUtils.getStrippedName(currentCompany
							.getPrimaryDomain()),
					CompanyNameUtils.getStrippedName(nextCompany
							.getPrimaryDomain()));

			if (companyScore >= 0) {
				if (sample2.haspMap.get(currentCompany.getId() + ","
						+ nextCompany.getId()) != null) {
					ConcurrentHashMap<String, String> value = sample2.haspMap
							.get(currentCompany.getId() + ","
									+ nextCompany.getId());
					value.put("PRIMARY_DOMAIN", String.valueOf(companyScore));
				} else {
					ConcurrentHashMap<String, String> value = new ConcurrentHashMap<String, String>(
							10);
					value.put("PRIMARY_DOMAIN", String.valueOf(companyScore));
					sample2.haspMap.put(currentCompany.getId() + ","
							+ nextCompany.getId(), value);
				}
			}
		}
	}

	private int calcualateStringDistance(String strippedName,
			String strippedName2) {
		if (strippedName == null || strippedName2 == null)
			return -1;
		return LevenshteinDistance.printDistance(strippedName, strippedName2);
	}

	public PrimaryDomainNameRunnable(Company currentCompany, Company nextCompany) {
		this.currentCompany = currentCompany;
		this.nextCompany = nextCompany;
	}

}
