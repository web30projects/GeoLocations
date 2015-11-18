/**
 * @since: Nov 8, 2015
 *
 */
package com.web3.geolocation.models;

import java.util.List;

/**
 * @author Jayanth
 *
 */
public class Locations extends BaseModel {

	private static final long serialVersionUID = 1L;
 	
	List<String> locationList;

	/**
	 * @return the locationList
	 */
	public List<String> getLocationList() {
		return locationList;
	}

	/**
	 * @param locationList the locationList to set
	 */
	public void setLocationList(List<String> locationList) {
		this.locationList = locationList;
	}
	
	

}
