

import java.sql.Date;


/**
 * 
 * @author Saravanakumar
 * 
 */

public class Company {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String shortName;
	private String website;
	private long hqLocation;
	private String tags;
	private String primaryDomain;
	private Object status;
	private Object priority;
	private String logo;
	private String description;
	private Date screenshotFetchedDate;
	private String screenshotUrl;
	private long userRequestCnt = 0;
	private long duplicateCompanyId;
	private Long cik;
	private int siteCrawled;
	private int logoFetched;
	private int topCompany;

	public int getLogoFetched() {
		return logoFetched;
	}

	public void setLogoFetched(int logoFetched) {
		this.logoFetched = logoFetched;
	}

	/**
	 * @return the siteCrawled
	 */
	public int getSiteCrawled() {
		return siteCrawled;
	}

	/**
	 * @param siteCrawled
	 *            the siteCrawled to set
	 */
	public void setSiteCrawled(int siteCrawled) {
		this.siteCrawled = siteCrawled;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name.trim();
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName
	 *            the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the hqLocation
	 */
	public long getHqLocation() {
		return hqLocation;
	}

	/**
	 * @param hqLocation
	 *            the hqLocation to set
	 */
	public void setHqLocation(long hqLocation) {
		this.hqLocation = hqLocation;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the primaryDomain
	 */
	public String getPrimaryDomain() {
		return primaryDomain;
	}

	/**
	 * @param primaryDomain
	 *            the primaryDomain to set
	 */
	public void setPrimaryDomain(String primaryDomain) {
		this.primaryDomain = primaryDomain;
	}

	public Object getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	public Object getPriority() {
		return priority;
	}

	public void setPriority(Object priority) {
		this.priority = priority;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the screenshotFetchedDate
	 */
	public Date getScreenshotFetchedDate() {
		return screenshotFetchedDate;
	}

	/**
	 * @param screenshotFetchedDate
	 *            the screenshotFetchedDate to set
	 */
	public void setScreenshotFetchedDate(Date screenshotFetchedDate) {
		this.screenshotFetchedDate = screenshotFetchedDate;
	}

	/**
	 * @return the screenshotUrl
	 */
	public String getScreenshotUrl() {
		return screenshotUrl;
	}

	/**
	 * @param screenshotUrl
	 *            the screenshotUrl to set
	 */
	public void setScreenshotUrl(String screenshotUrl) {
		this.screenshotUrl = screenshotUrl;
	}

	public long getUserRequestCnt() {
		return userRequestCnt;
	}

	public void setUserRequestCnt(long userRequestCnt) {
		this.userRequestCnt = userRequestCnt;
	}

	/**
	 * @return the duplicateCompanyId
	 */
	public long getDuplicateCompanyId() {
		return duplicateCompanyId;
	}

	/**
	 * @param duplicateCompanyId
	 *            the duplicateCompanyId to set
	 */
	public void setDuplicateCompanyId(long duplicateCompanyId) {
		this.duplicateCompanyId = duplicateCompanyId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [name=");
		builder.append(name);
		builder.append(", short_name=");
		builder.append(shortName);
		builder.append(", website=");
		builder.append(website);
		builder.append(", hqLocation=");
		builder.append(hqLocation);
		builder.append(", tags=");
		builder.append(tags);
		builder.append(", primaryDomain=");
		builder.append(primaryDomain);
		builder.append(", status=");
		builder.append(status);
		builder.append(", priority=");
		builder.append(priority);
		builder.append(", logo=");
		builder.append(logo);
		builder.append(", description=");
		builder.append(description);
		builder.append(", screenshotFetchedDate=");
		builder.append(screenshotFetchedDate);
		builder.append(", screenshotUrl=");
		builder.append(screenshotUrl);
		builder.append(", userRequestCnt=");
		builder.append(userRequestCnt);
		builder.append(", duplicateCompanyId=");
		builder.append(duplicateCompanyId);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


	/**
	 * @return the cik
	 */
	public Long getCik() {
		return cik;
	}

	/**
	 * @param cik
	 *            the cik to set
	 */
	public void setCik(Long cik) {
		this.cik = cik;
	}

	public int getTopCompany() {
		return topCompany;
	}

	public void setTopCompany(int topCompany) {
		this.topCompany = topCompany;
	}

}