/**
 * @since: Nov 7, 2015
 *
 */
package com.web3.geolocation.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.web3.geolocation.common.AppConstants;
import com.web3.geolocation.service.GeoExtractorServiceI;
import com.web3.geolocation.vo.BaseResponseVO;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

/**
 * @author Jayanth
 *
 */
public class GeoExtractorService implements GeoExtractorServiceI {
	
	@Value("${model.location}")
	private String TRAINED_MODEL;
	
	private static NameFinderME finder=null;
	
	/* (non-Javadoc)
	 * @see com.web3.geolocation.service.LocationFinderServiceI#findLocations(java.lang.String)
	 */
	@Override
	public List<String> findLocations(String textToExtract) throws Exception {
		Span[] locSpans =null;
		loadNameFinderModel();
		String tokenizedText[] = SimpleTokenizer.INSTANCE.tokenize(textToExtract);
		locSpans = finder.find(tokenizedText);
		//System.out.println("Loc >>> " + Arrays.asList(Span.spansToStrings(locSpans, tokenizedText)));
		return Arrays.asList(Span.spansToStrings(locSpans, tokenizedText));
	}
	
	/* (non-Javadoc)
	 * @see com.web3.geolocation.service.GeoExtractorServiceI#extractGeoInformation(java.lang.String)
	 */
	@Override
	public BaseResponseVO extractGeoInformation(String textToExtract) {
		BaseResponseVO responseVO = new BaseResponseVO();
		List<String> extractedlocs = new ArrayList<String>();
		try {
			extractedlocs = findLocations(textToExtract);
		} catch (Exception e) {
			responseVO.setStatus(AppConstants.FAILURE);
			responseVO.setMessage(e.getMessage());
		}
		responseVO.setTotalCount(extractedlocs.size());
		responseVO.setRecords(extractedlocs);
		return responseVO;
	}
	
	
	private void loadNameFinderModel() throws InvalidFormatException, IOException {
		InputStream inputStream = null;
		try {
			if (finder == null) {
				inputStream = new FileInputStream(new File(TRAINED_MODEL));
				TokenNameFinderModel locationFinder = new TokenNameFinderModel(inputStream);
				finder = new NameFinderME(locationFinder);
			}
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}

	}

	

	/*public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("G:\\Web 3.0\\Workspace\\GeoLocation\\src\\main\\resources\\tweets.txt"));
		try{
		    while(scan.hasNextLine()){
		        String line = scan.nextLine();
		        System.out.println("Input >>> " + line);
		        new LocationFinderService().findLocations(line);
		    }
		}
		finally {
			scan.close();
		}
	}*/

	

}
