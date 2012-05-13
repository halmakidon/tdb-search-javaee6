package tdb.search.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 検索キーを表すクラス- イミュータブル
 *
 * @author sekky
 *
 */
@Entity
@Cacheable(true)
public class Key extends BaseEntity {

	private static final long serialVersionUID = -4976341476730921130L;

	protected Key() {
	}

	public Key(String word) {
		this.word = word;
	}

	/** key名 */
	@Id
	private String word;

	public String getWord() {
		return word;
	}

	@SuppressWarnings("unused")
	private void setWord(String word) {
		this.word = word;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		Key other = (Key) obj;
		if (word == null) {
			if (other.word != null) {
				return false;
			}
		} else if (!word.equals(other.word)) {
			return false;
		}
		return true;
	}
}
