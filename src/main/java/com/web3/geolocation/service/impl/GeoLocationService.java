/**
 * @since: Oct 31, 2015
 *
 */
package com.web3.geolocation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.geonames.InsufficientStyleException;
import org.geonames.PostalCode;
import org.geonames.PostalCodeSearchCriteria;
import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.geonames.WikipediaArticle;
import org.springframework.beans.factory.annotation.Value;

import com.web3.geolocation.common.AppConstants;
import com.web3.geolocation.models.GeoDetails;
import com.web3.geolocation.models.WikiDetails;
import com.web3.geolocation.service.GeoLocationServiceI;
import com.web3.geolocation.vo.GeoDetailsResponseVO;

/**
 * @author Jayanth
 *
 */
public class GeoLocationService implements GeoLocationServiceI {
	
	@Value("${geonames.user}")
	private String GEO_USER;
	
	@Value("${geonames.radius}")
	private Double RADIUS;

	@Value("${geonames.lang}")
	private String LANG;
	
	@Value("${geonames.maxrows}")
	private Integer MAX_ROWS;

	/* (non-Javadoc)
	 * @see com.web3.geolocation.service.GeoLocationServiceI#retrieveGeoInformation(java.lang.String)
	 */
	@Override
	public GeoDetailsResponseVO retrieveGeoInformation(String location) {
		GeoDetailsResponseVO responseVO = new GeoDetailsResponseVO();
		List<GeoDetails> geoDetailList = new ArrayList<GeoDetails>();
		ToponymSearchResult searchResult =null;
		try {
			searchResult = searchGeoNames(location);
			geoDetailList = populateGeoDetails(searchResult.getToponyms());
		} catch (Exception e) {
			responseVO.setStatus(AppConstants.FAILURE);
			responseVO.setMessage(e.getMessage());
		}
		responseVO.setTotalCount(geoDetailList.size());
		responseVO.setRecords(geoDetailList);
		return responseVO;
	}

	/* (non-Javadoc)
	 * @see com.web3.geolocation.service.GeoLocationServiceI#retrievePlaceFromCoOrdinates(java.lang.Double, java.lang.Double)
	 */
	@Override
	public GeoDetailsResponseVO retrievePlaceFromCoOrdinates(Double lat, Double lng) {
		GeoDetails geoDetails = null; 
		GeoDetailsResponseVO responseVO = new GeoDetailsResponseVO();
		List<GeoDetails> geoDetailList =new ArrayList<GeoDetails>();
		try {
			List<PostalCode> postalCodes = retrievePostalCode(lat, lng);
			for(PostalCode postalCode:postalCodes){
				geoDetails = new GeoDetails();
				geoDetails.setName(postalCode.getPlaceName());
				geoDetails.setLatitude(postalCode.getLatitude());
				geoDetails.setLongitude(postalCode.getLongitude());
				geoDetailList.add(geoDetails);
			}
		} catch (Exception e) {
			responseVO.setStatus(AppConstants.FAILURE);
			responseVO.setMessage(e.getMessage());
		}
		responseVO.setTotalCount(geoDetailList.size());
		responseVO.setRecords(geoDetailList);
		return responseVO;
	}

	
	/* (non-Javadoc)
	 * @see com.web3.geolocation.service.GeoLocationServiceI#retrieveNearByPlace(java.lang.Double, java.lang.Double)
	 */
	@Override
	public GeoDetailsResponseVO retrieveNearByPlace(Double lat, Double lng) {
		GeoDetailsResponseVO responseVO = new GeoDetailsResponseVO();
		List<GeoDetails> geoDetailList =null;
		List<Toponym> toponyms;
		try {
			toponyms = retrieveNearByPlaces(lat, lng, RADIUS, MAX_ROWS);
			geoDetailList = populateGeoDetails(toponyms);
		} catch (Exception e) {
			responseVO.setStatus(AppConstants.FAILURE);
			responseVO.setMessage(e.getMessage());
		}
		responseVO.setTotalCount(geoDetailList.size());
		responseVO.setRecords(geoDetailList);
		return responseVO;
	}
	
	/* (non-Javadoc)
	 * @see com.web3.geolocation.service.GeoLocationServiceI#retrieveWikiPediaArticles(java.lang.String, java.lang.String)
	 */
	@Override
	public GeoDetailsResponseVO retrieveWikiArticles(String location, String language) {
		GeoDetailsResponseVO responseVO = new GeoDetailsResponseVO();
		List<WikiDetails> wikiDetailList =null;
		try {
			List<WikipediaArticle> wikiArticleList = retrieveWikipediaArticles(location, language);
			wikiDetailList = populateWikiDetails(wikiArticleList);
		} catch (Exception e) {
			responseVO.setStatus(AppConstants.FAILURE);
			responseVO.setMessage(e.getMessage());
		}
		responseVO.setTotalCount(wikiDetailList.size());
		responseVO.setRecords(wikiDetailList);
		return responseVO;
	}
	
	/* (non-Javadoc)
	 * @see com.web3.geolocation.service.GeoLocationServiceI#retrieveNearbyWikiArticles(java.lang.Double, java.lang.Double, java.lang.String)
	 */
	@Override
	public GeoDetailsResponseVO retrieveNearbyWikiArticles(Double lat, Double lng, String language) {
		GeoDetailsResponseVO responseVO = new GeoDetailsResponseVO();
		List<WikiDetails> wikiDetailList =null;
		try {
			List<WikipediaArticle> wikiArticleList = retrieveNearbyWikipediaArticles(lat, lng, language);
			wikiDetailList = populateWikiDetails(wikiArticleList);
		} catch (Exception e) {
			responseVO.setStatus(AppConstants.FAILURE);
			responseVO.setMessage(e.getMessage());
		}
		responseVO.setTotalCount(wikiDetailList.size());
		responseVO.setRecords(wikiDetailList);
		return responseVO;
	}

	private List<GeoDetails> populateGeoDetails(List<Toponym> toponyms)
			throws InsufficientStyleException {
		GeoDetails geoDetails=null;
		List<GeoDetails> geoDetailList =new ArrayList<GeoDetails>();
		for(Toponym toponym:toponyms){
			geoDetails = new GeoDetails();
			geoDetails.setGeoNameId(toponym.getGeoNameId());
			geoDetails.setName(toponym.getName());
			geoDetails.setAlternatNames(toponym.getAlternateNames());
			geoDetails.setLatitude(toponym.getLatitude());
			geoDetails.setLongitude(toponym.getLongitude());
			geoDetails.setCountryCode(toponym.getCountryCode());
			geoDetails.setTimezone(toponym.getTimezone());
			geoDetailList.add(geoDetails);
		}
		return geoDetailList;
	}
	
	private List<WikiDetails> populateWikiDetails(List<WikipediaArticle> wikipediaArticles){
		List<WikiDetails> wikiDetailList = new ArrayList<WikiDetails>();
		WikiDetails wikipediaDetails = null;
		for(WikipediaArticle article:wikipediaArticles){
			wikipediaDetails = new WikiDetails();
			wikipediaDetails.setSummary(article.getSummary());
			wikipediaDetails.setThumbnailImg(article.getThumbnailImg());
			wikipediaDetails.setUrl(article.getWikipediaUrl());
			wikipediaDetails.setTitle(article.getTitle());
			wikiDetailList.add(wikipediaDetails);
		}
		return wikiDetailList;
	}
	
	private List<PostalCode> retrievePostalCode(Double lat, Double lng) throws Exception{
		PostalCodeSearchCriteria searchCriteria = new PostalCodeSearchCriteria();
		searchCriteria.setLatitude(lat);
		searchCriteria.setLongitude(lng);
		searchCriteria.setRadius(RADIUS);
		WebService.setUserName(GEO_USER);
		return WebService.findNearbyPostalCodes(searchCriteria);
	}
	
	private List<Toponym> retrieveNearByPlaces(Double lat,Double lng,Double radius, Integer maxRows) throws Exception{
		WebService.setUserName(GEO_USER);
		return WebService.findNearbyPlaceName(lat, lng, radius, maxRows);
	}

	private ToponymSearchResult searchGeoNames(String location) throws Exception {
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(location);
		searchCriteria.setStyle(Style.FULL);
		WebService.setUserName(GEO_USER);
		return WebService.search(searchCriteria);
	}
	
	private List<WikipediaArticle> retrieveWikipediaArticles(String location, String language) throws Exception{
		WebService.setUserName(GEO_USER);
		if(language==null){
			language=LANG;
		}
		return WebService.wikipediaSearch(location, language);
	}

	
	private List<WikipediaArticle> retrieveNearbyWikipediaArticles(Double lat,Double lng,String language) throws Exception{
		WebService.setUserName(GEO_USER);
		if(language==null){
			language=LANG;
		}
		return WebService.findNearbyWikipedia(lat,lng,language);
	}
	
}
