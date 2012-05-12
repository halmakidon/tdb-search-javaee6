package tdb.search.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Company extends BaseEntity {

	private static final long serialVersionUID = 8915758995568293559L;

	/** 企業コード */
	@Id
	private String code;

	/** 検索キー候補 */
	@ManyToMany
	@JoinTable(name = "KEY_COMPANE", joinColumns = @JoinColumn(name = "company_code", referencedColumnName = "code"), inverseJoinColumns = @JoinColumn(name = "key_word", referencedColumnName = "word"))
	private Set<Key> keys;

	/** 会社名 */
	private String name;

	/** 住所 */
	private String address;

	/** 業種 */
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
	 * @return the keys
	 */
	public final Set<Key> getKeys() {
		return keys;
	}

	/**
	 * @param keys
	 *            the keys to set
	 */
	public final void setKeys(Set<Key> keys) {
		this.keys = keys;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Company other = (Company) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		return true;
	}
}