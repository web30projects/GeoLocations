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
import org.springframework.http.HttpEntity;
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
@SpringApplicationConfiguration(classes=GeoLocationApp.class)
@WebIntegrationTest
public class GeoExtractionControllerTest {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testExtactGeoInformationApi(){
		String tweet = "My mom is in Miami soaking up the sun while I'm here in Houston drowning. #HurricanePatricia";
			String [] expected = {"Miami","Houston"};
		   HttpEntity<String> httpEntity = new HttpEntity<String>(tweet);
		   ResponseEntity<BaseResponseVO> response = restTemplate.postForEntity("http://localhost:8080/geoimagery/api/extractgeo", httpEntity, BaseResponseVO.class);
		   BaseResponseVO baseResponse = response.getBody();
		   Assert.assertTrue(baseResponse.getRecords().size() == 2);
		   Assert.assertArrayEquals(expected,baseResponse.getRecords().toArray());
	}

}
