package fr.diginamic.model;

public class Pays {

	Integer locationId;
	String locationCode;
	String locationNameShortEn;

	/**
	 * Constructeur
	 * 
	 * @param locationId
	 * @param locationCode
	 * @param locationNameShortEn
	 */
	public Pays(Integer locationId, String locationCode, String locationNameShortEn) {
		super();
		this.locationId = locationId;
		this.locationCode = locationCode;
		this.locationNameShortEn = locationNameShortEn;
	}

	/**
	 * @return the locationId
	 */
	public Integer getLocationId() {
		return locationId;
	}

	/**
	 * Setter
	 * 
	 * @param locationId
	 *            the locationId to set
	 */
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationCode
	 */
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * Setter
	 * 
	 * @param locationCode
	 *            the locationCode to set
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * @return the locationNameShortEn
	 */
	public String getLocationNameShortEn() {
		return locationNameShortEn;
	}

	/**
	 * Setter
	 * 
	 * @param locationNameShortEn
	 *            the locationNameShortEn to set
	 */
	public void setLocationNameShortEn(String locationNameShortEn) {
		this.locationNameShortEn = locationNameShortEn;
	}

}
