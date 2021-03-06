package org.sagebionetworks.warehouse.workers.db.audit;

import java.util.Date;
import java.util.List;

import org.sagebionetworks.warehouse.workers.model.UserActivityPerMonth;

public interface UserActivityPerMonthDao {

	/**
	 * 
	 * @return a UserActivityPerMonth given the userId and month
	 */
	public UserActivityPerMonth get(Long userId, String month);

	/**
	 * Truncate all of the data.
	 */
	public void truncateAll();

	/**
	 * 
	 * @param month
	 * @return true if there exists records for prevMonth in the database,
	 *         false otherwise.
	 */
	public boolean hasRecordForMonth(Date month);

	/**
	 * Insert on duplicate key update a batch of UserActivityPerMonth to the DB.
	 * 
	 * @param batch
	 */
	public void insert(List<UserActivityPerMonth> batch);
}
