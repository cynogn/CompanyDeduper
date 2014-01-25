import java.util.Collections;
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

	@Override
	public void run() {
		int companyScore = calcualateStringDistance(
				CompanyNameUtils.getStrippedName(currentCompany.getName()),
				CompanyNameUtils.getStrippedName(nextCompany.getName()));
//		System.out.println(currentCompany.getId() + "," + nextCompany.getId()
//				+ "==" + "COMPANY NAME is" + companyScore);

		if (sample2.haspMap.get(currentCompany.getId() + ","
				+ nextCompany.getId()) != null) {
			ConcurrentHashMap<String, String> value = sample2.haspMap
					.get(currentCompany.getId() + "," + nextCompany.getId());
			value.put("COMPANY_NAME", String.valueOf(companyScore));
		} else {
			ConcurrentHashMap<String, String> value = new ConcurrentHashMap<String, String>(10);
			value.put("COMPANY_NAME", String.valueOf(companyScore));
			sample2.haspMap.put(
					currentCompany.getId() + "," + nextCompany.getId(), value);
			sample2.haspMap.put(
					currentCompany.getId() + "," + nextCompany.getId(), value);
		}
		if (sample2.haspMap.get(currentCompany.getId() + ","
				+ nextCompany.getId()) != null) {
			ConcurrentHashMap<String, String> value = sample2.haspMap
					.get(currentCompany.getId() + "," + nextCompany.getId());
			value.put("COMPANY_1_COMPANY_NAME", currentCompany.getName());
			value.put("COMPANY_2_COMPANY_NAME", nextCompany.getName());

			value.put("COMPANY_1_SHORT_NAME", currentCompany.getShortName());
			value.put("COMPANY_2_SHORT_NAME", nextCompany.getShortName());

			value.put("COMPANY_1_CIK_NUMBER",
					String.valueOf(currentCompany.getCik()));
			value.put("COMPANY_2_CIK_NUMBER",
					String.valueOf(nextCompany.getCik()));

			value.put("COMPANY_1_COMPANY_ID",
					String.valueOf(currentCompany.getId()));
			value.put("COMPANY_2_COMPANY_ID",
					String.valueOf(nextCompany.getId()));
			
			value.put("COMPANY_1_COMPANY_PRIMARY_DOMAIN_URL",
					String.valueOf(currentCompany.getPrimaryDomain()));
			value.put("COMPANY_1_COMPANY_PRIMARY_DOMAIN_URL",
					String.valueOf(nextCompany.getPrimaryDomain()));
		}
	}

	private int calcualateStringDistance(String strippedName,
			String strippedName2) {
		return LevenshteinDistance.printDistance(strippedName, strippedName2);
	}

	public PrimaryNameRunnable(Company currentCompany, Company nextCompany) {
		this.currentCompany = currentCompany;
		this.nextCompany = nextCompany;
	}

}
