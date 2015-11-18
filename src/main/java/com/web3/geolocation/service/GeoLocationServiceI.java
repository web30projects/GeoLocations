/**
 * @since: Oct 31, 2015
 *
 */
package com.web3.geolocation.service;

import com.web3.geolocation.vo.GeoDetailsResponseVO;

/**
 * @author Jayanth
 *
 */
public interface GeoLocationServiceI {
	
	public GeoDetailsResponseVO retrieveGeoInformation(String location);
	public GeoDetailsResponseVO retrievePlaceFromCoOrdinates(Double lat,Double lng);
	public GeoDetailsResponseVO retrieveNearByPlace(Double lat,Double lng);
	public GeoDetailsResponseVO retrieveWikiArticles(String location,String language);
	public GeoDetailsResponseVO retrieveNearbyWikiArticles(Double lat, Double lng,String language);
}
