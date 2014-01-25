import java.util.Collections;
import java.util.HashMap;
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
public class CikNumberRunnable implements Runnable {
	Company currentCompany, nextCompany;

	@Override
	public void run() {
		if(currentCompany.getCik() > 0 && nextCompany.getCik() > 0) {
		int companyScore = (currentCompany.getCik() == nextCompany.getCik()) ? 1 : 0;
		
//		System.out.println(currentCompany.getCik()+","+nextCompany.getCik()+"=="+"CIK NUMBER is"+companyScore);
		
		if (sample2.haspMap.get(currentCompany.getId() + ","
				+ nextCompany.getId()) != null) {
			ConcurrentHashMap<String, String> value = sample2.haspMap
					.get(currentCompany.getId() + "," + nextCompany.getId());
			value.put("CIK_NUMBER", String.valueOf(companyScore));
		} else {
			ConcurrentHashMap<String, String> value = new ConcurrentHashMap<String, String>(10);
			value.put("CIK_NUMBER", String.valueOf(companyScore));
			sample2.haspMap.put(
					currentCompany.getId() + "," + nextCompany.getId(), value);
		}
		}
	}




	public CikNumberRunnable(Company currentCompany, Company nextCompany) {
		this.currentCompany = currentCompany;
		this.nextCompany = nextCompany;
	}

}
