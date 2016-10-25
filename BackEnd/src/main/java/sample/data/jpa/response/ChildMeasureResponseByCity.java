package sample.data.jpa.response;

import java.io.Serializable;
import java.util.List;

public class ChildMeasureResponseByCity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680838968178381501L;

	private String cityName;
	private double cityChildMeasure;
	private double cityFamilyMeasure;
	private double cityCommunityMeasure;
	private List<County> countyList;

	private ErrorHandler errorHandler;

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the cityChildMeasure
	 */
	public double getCityChildMeasure() {
		return cityChildMeasure;
	}

	/**
	 * @param cityChildMeasure
	 *            the cityChildMeasure to set
	 */
	public void setCityChildMeasure(double cityChildMeasure) {
		this.cityChildMeasure = cityChildMeasure;
	}

	/**
	 * @return the countyList
	 */
	public List<County> getCountyList() {
		return countyList;
	}

	/**
	 * @param countyList
	 *            the countyList to set
	 */
	public void setCountyList(List<County> countyList) {
		this.countyList = countyList;
	}

	public double getCityFamilyMeasure() {
		return cityFamilyMeasure;
	}

	public void setCityFamilyMeasure(double cityFamilyMeasure) {
		this.cityFamilyMeasure = cityFamilyMeasure;
	}

	public double getCityCommunityMeasure() {
		return cityCommunityMeasure;
	}

	public void setCityCommunityMeasure(double cityCommunityMeasure) {
		this.cityCommunityMeasure = cityCommunityMeasure;
	}

	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}
}
