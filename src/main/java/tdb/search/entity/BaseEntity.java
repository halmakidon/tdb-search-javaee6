package tdb.search.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@EntityListeners(TimestampListener.class)
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8720320305152474038L;

	/** バージョン */
	@Version
	private Long version;

	/** 作成日時 */
	private Date createDate;

	/** 更新日時 */
	private Date updateDate;

	/**
	 * @return the version
	 */
	public final Long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	@SuppressWarnings("unused")
	private final void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the createDate
	 */
	public final Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	final void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateDate
	 */
	public final Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	final void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
