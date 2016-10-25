package sample.data.jpa.response;

import java.util.List;
import java.math.*;

public class County {
	private String countyName;
	private double countyChildMeasure;
	private double countyFamilyMeasure;
	private double countyCommunityMeasure;
	private List<BigDecimal[]> coordinates;
	private List<ZipCode> zipCodeList;
	
	/**
	 * @return the countyName
	 */
	public String getCountyName() {
		return countyName;
	}
	/**
	 * @param countyName the countyName to set
	 */
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	/**
	 * @return the countyChildMeasure
	 */
	public double getCountyChildMeasure() {
		return countyChildMeasure;
	}
	/**
	 * @param countyChildMeasure the countyChildMeasure to set
	 */
	public void setCountyChildMeasure(double countyChildMeasure) {
		this.countyChildMeasure = countyChildMeasure;
	}
	public double getCountyFamilyMeasure() {
		return countyFamilyMeasure;
	}
	public void setCountyFamilyMeasure(double countyFamilyMeasure) {
		this.countyFamilyMeasure = countyFamilyMeasure;
	}
	public double getCountyCommunityMeasure() {
		return countyCommunityMeasure;
	}
	public void setCountyCommunityMeasure(double countyCommunityMeasure) {
		this.countyCommunityMeasure = countyCommunityMeasure;
	}

	public List<BigDecimal[]> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<BigDecimal[]> coordinates) {
		this.coordinates = coordinates;
	}
	public List<ZipCode> getZipCodeList() {
		return zipCodeList;
	}
	public void setZipCodeList(List<ZipCode> zipCodeList) {
		this.zipCodeList = zipCodeList;
	}
	
	
}
