/**
 * @since: Nov 7, 2015
 *
 */
package com.web3.geolocation.service;

import java.util.List;

import com.web3.geolocation.vo.BaseResponseVO;

/**
 * @author Jayanth
 *
 */
public interface GeoExtractorServiceI {
	
	public List<String> findLocations(String textToExtract) throws Exception;
	public BaseResponseVO extractGeoInformation(String textToExtract);

}
