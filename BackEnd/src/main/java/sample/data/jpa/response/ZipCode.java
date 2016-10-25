package sample.data.jpa.response;

import java.util.List;
import java.math.*;

public class ZipCode {
	private String zipCode;
	private double countyChildMeasure;
	private double countyFamilyMeasure;
	private double countyCommunityMeasure;
	private List<BigDecimal[]> coordinates;
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public double getCountyChildMeasure() {
		return countyChildMeasure;
	}
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
	
	
	
	
	
}
