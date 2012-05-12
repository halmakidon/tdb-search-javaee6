package tdb.search.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import tdb.search.util.Log;
import tdb.search.util.SystemDate;

public class TimestampListener {

	private static final Logger LOGGER = Log.getLogger(TimestampListener.class);


	public TimestampListener () {

		if(LOGGER.isLoggable(Level.FINE) ){
			LOGGER.fine(TimestampListener.class.getName() + "を生成");
		}

	}

	@PreUpdate
	@PrePersist
	public void setTimestamp(BaseEntity entity) {
		// 生成日がない場合は記入する
		if (entity.getCreateDate() == null) {
			entity.setCreateDate(SystemDate.getDate());
		}
		entity.setUpdateDate(SystemDate.getDate());
	}

}
