/**
 * @since: Nov 1, 2015
 *
 */
package com.web3.geolocation.models;

/**
 * @author Jayanth
 *
 */
public class WikiDetails extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private String summary;
	private String thumbnailImg;
	private String title;
	private String url;
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the thumbnailImg
	 */
	public String getThumbnailImg() {
		return thumbnailImg;
	}
	/**
	 * @param thumbnailImg the thumbnailImg to set
	 */
	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
