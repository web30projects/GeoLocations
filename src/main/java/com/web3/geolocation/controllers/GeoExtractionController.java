/**
 * @since: Nov 8, 2015
 *
 */
package com.web3.geolocation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web3.geolocation.service.GeoExtractorServiceI;
import com.web3.geolocation.vo.BaseResponseVO;

/**
 * @author Jayanth
 *
 */

@RestController
@RequestMapping(value="/api/extractgeo")
public class GeoExtractionController {
	
	@Autowired
	GeoExtractorServiceI geoExtractorService;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public BaseResponseVO extactGeoInformation(@RequestBody String textToExtract){
		return geoExtractorService.extractGeoInformation(textToExtract);
		
	}
	

}
