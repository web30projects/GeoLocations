/**
 * @since: Nov 16, 2015
 *
 */
package com.web3.geolocation.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.web3.geolocation.GeoLocationApp;
import com.web3.geolocation.vo.BaseResponseVO;

/**
 * @author Jayanth
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GeoLocationApp.class)
@WebIntegrationTest
public class GeoLocationControllerTest {

	private RestTemplate restTemplate = new RestTemplate();
	
	
	@Test
	public void testGetGeoLocationDetailsApi(){
		String location = "dayton";
		ResponseEntity<BaseResponseVO> response = restTemplate
				.getForEntity("http://localhost:8080/geoimagery/api/geolocation/" + location, BaseResponseVO.class);
		 BaseResponseVO baseResponseVO = response.getBody();
		 
		 Assert.assertNotNull(baseResponseVO);
		 Assert.assertTrue(baseResponseVO.getRecords().size() > 0 );
	}
	
	@Test
	public void testGetPlaceFromCoOrdinatesApi(){
		 String lat = "39.75895";
		 String lng = "-84.19161";
		 ResponseEntity<BaseResponseVO> response = restTemplate.getForEntity("http://localhost:8080/geoimagery/api/geolocation/"+lat+"/"+lng+"/", BaseResponseVO.class);
		 BaseResponseVO baseResponseVO = response.getBody();
		 
		 Assert.assertNotNull(baseResponseVO);
		 Assert.assertTrue(baseResponseVO.getRecords().size() > 0 );
	}
	
	@Test
	public void testGetNearByPlace(){
		 String lat = "39.75895";
		 String lng = "-84.19161";
		ResponseEntity<BaseResponseVO> response = restTemplate.getForEntity(
				"http://localhost:8080/geoimagery/api/geolocation/nearByPlace/" + lat + "/" + lng + "/",
				BaseResponseVO.class);
		 BaseResponseVO baseResponseVO = response.getBody();
		 
		 Assert.assertNotNull(baseResponseVO);
		 Assert.assertTrue(baseResponseVO.getRecords().size() > 0 );
	}
	
	@Test
	public void testGetWikiArticles(){
		 String location = "dayton";
		 String language = "en";
		ResponseEntity<BaseResponseVO> response = restTemplate.getForEntity(
				"http://localhost:8080/geoimagery/api/geolocation/wikidetails/" + location + "/" + language + "/",
				BaseResponseVO.class);
		 BaseResponseVO baseResponseVO = response.getBody();
		 
		 Assert.assertNotNull(baseResponseVO);
		 Assert.assertTrue(baseResponseVO.getRecords().size() > 0 );
	}
	
	@Test
	public void testGetNearbyWikiArticles(){
		 String lat = "39.75895";
		 String lng = "-84.19161";
		 String language = "en";
		ResponseEntity<BaseResponseVO> response = restTemplate
				.getForEntity("http://localhost:8080/geoimagery/api/geolocation/nearbywikidetails/" + lat + "/" + lng
						+ "/" + language + "/", BaseResponseVO.class);
		 BaseResponseVO baseResponseVO = response.getBody();
		 
		 Assert.assertNotNull(baseResponseVO);
		 Assert.assertTrue(baseResponseVO.getRecords().size() > 0 );
	}
}
