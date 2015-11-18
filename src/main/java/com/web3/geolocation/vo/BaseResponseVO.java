/**
 * @since: Oct 31, 2015
 *
 */
package com.web3.geolocation.vo;

import java.io.Serializable;
import java.util.List;

import com.web3.geolocation.common.AppConstants;

/**
 * @author Jayanth
 *
 */
public class BaseResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer totalCount;
	private List<? extends Object> records;
	private String message;
	private String status = AppConstants.SUCCESS;
	
	
	
	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the records
	 */
	public List<? extends Object> getRecords() {
		return records;
	}
	/**
	 * @param records the records to set
	 */
	public void setRecords(List<? extends Object> records) {
		this.records = records;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
}
