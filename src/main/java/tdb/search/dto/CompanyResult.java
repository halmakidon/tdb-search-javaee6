package tdb.search.dto;

import java.io.Serializable;

/**
 * 結果返却用のCompanyオブジェクト
 *
 * @author sekky
 *
 */
public class CompanyResult implements Serializable {

	private static final long serialVersionUID = -3920288284434129323L;
	private String code;
	private String name;
	private String address;
	private String type;

	/**
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public final void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public final String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public final void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public final void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompanyResult [code=" + code + ", name=" + name + ", address="
				+ address + ", type=" + type + "]";
	}
}
