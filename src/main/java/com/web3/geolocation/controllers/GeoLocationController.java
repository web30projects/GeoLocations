/**
 * @since: Oct 31, 2015
 *
 */
package com.web3.geolocation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web3.geolocation.service.GeoLocationServiceI;
import com.web3.geolocation.vo.BaseResponseVO;

/**
 * @author Jayanth
 */
@RestController 
@RequestMapping(value="/api/geolocation")
public class GeoLocationController {
	
	@Autowired
	GeoLocationServiceI geoLocationService;
	
	@RequestMapping(method=RequestMethod.GET, value="{location}")
	public BaseResponseVO getGeoLocationDetails(@PathVariable String location){
		return geoLocationService.retrieveGeoInformation(location);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="{lat}/{lng}")
	public BaseResponseVO getPlaceFromCoOrdinates(@PathVariable Double lat, @PathVariable Double lng){
		return geoLocationService.retrievePlaceFromCoOrdinates(lat, lng);
	}
	@RequestMapping(method=RequestMethod.GET, value="/nearByPlace/{lat}/{lng}")
	public BaseResponseVO getNearByPlace(@PathVariable Double lat, @PathVariable Double lng){
		return geoLocationService.retrieveNearByPlace(lat, lng);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/wikidetails/{location}/{language}")
	public BaseResponseVO getWikiArticles(@PathVariable String location, @PathVariable String language){
		return geoLocationService.retrieveWikiArticles(location, language);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/nearbywikidetails/{lat}/{lng}/{language}")
	public BaseResponseVO getNearbyWikiArticles(@PathVariable Double lat, @PathVariable Double lng, @PathVariable String language){
		return geoLocationService.retrieveNearbyWikiArticles(lat, lng, language);
	}

}
