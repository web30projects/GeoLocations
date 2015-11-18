/**
 * @since: Oct 31, 2015
 *
 */
package com.web3.geolocation.models;

import org.geonames.Timezone;

/**
 * @author Jayanth
 *
 */
public class GeoDetails extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	private Integer geoNameId;
	private String name;
	private String asciiname;
	private String alternatNames;
	private Double latitude;
	private Double longitude;
	private String countryCode;
	private Timezone timezone;
	/**
	 * @return the geoNameId
	 */
	public Integer getGeoNameId() {
		return geoNameId;
	}
	/**
	 * @param geoNameId the geoNameId to set
	 */
	public void setGeoNameId(Integer geoNameId) {
		this.geoNameId = geoNameId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the asciiname
	 */
	public String getAsciiname() {
		return asciiname;
	}
	/**
	 * @param asciiname the asciiname to set
	 */
	public void setAsciiname(String asciiname) {
		this.asciiname = asciiname;
	}
	/**
	 * @return the alternatNames
	 */
	public String getAlternatNames() {
		return alternatNames;
	}
	/**
	 * @param alternatNames the alternatNames to set
	 */
	public void setAlternatNames(String alternatNames) {
		this.alternatNames = alternatNames;
	}
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the timezone
	 */
	public Timezone getTimezone() {
		return timezone;
	}
	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(Timezone timezone) {
		this.timezone = timezone;
	}
	
	

}
