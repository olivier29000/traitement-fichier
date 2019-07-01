package fr.diginamic.model;

public class ProduitSitc {

	Integer productId;
	String productCode;
	String productNameShortEn;
	Integer productType;
	String productTypeString;

	/**
	 * Constructeur
	 * 
	 * @param productId
	 * @param productCode
	 * @param productNameShortEn
	 * @param productType
	 * @param productTypeString
	 */
	public ProduitSitc(Integer productId, String productCode, String productNameShortEn, Integer productType,
			String productTypeString) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.productNameShortEn = productNameShortEn;
		this.productType = productType;
		this.productTypeString = productTypeString;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * Setter
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Setter
	 * 
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the productNameShortEn
	 */
	public String getProductNameShortEn() {
		return productNameShortEn;
	}

	/**
	 * Setter
	 * 
	 * @param productNameShortEn
	 *            the productNameShortEn to set
	 */
	public void setProductNameShortEn(String productNameShortEn) {
		this.productNameShortEn = productNameShortEn;
	}

	/**
	 * @return the productType
	 */
	public Integer getProductType() {
		return productType;
	}

	/**
	 * Setter
	 * 
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	/**
	 * @return the productTypeString
	 */
	public String getProductTypeString() {
		return productTypeString;
	}

	/**
	 * Setter
	 * 
	 * @param productTypeString
	 *            the productTypeString to set
	 */
	public void setProductTypeString(String productTypeString) {
		this.productTypeString = productTypeString;
	}

}
