package sample.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "COUNTY_MASTER")
public class CountyMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6415289531956517088L;

	@Id
	@GeneratedValue
	@Column(name = "COUNTY_ID", unique = true, length = 100, nullable = false)
	private Long id;

	@Column(name = "COUNTY_NAME", length = 100, nullable = false)
	private String countyName;

	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private CityMasterEntity cityMasterEntity;
	
	protected CountyMasterEntity() {
	}

	public CountyMasterEntity(String countyName, CityMasterEntity cityMasterEntity) {
		super();
		this.countyName = countyName;
		this.cityMasterEntity = cityMasterEntity;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the countyName
	 */
	public String getCountyName() {
		return countyName;
	}

	/**
	 * @param countyName
	 *            the countyName to set
	 */
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	/**
	 * @return the cityMasterEntity
	 */
	public CityMasterEntity getCityMasterEntity() {
		return cityMasterEntity;
	}

	/**
	 * @param cityMasterEntity the cityMasterEntity to set
	 */
	public void setCityMasterEntity(CityMasterEntity cityMasterEntity) {
		this.cityMasterEntity = cityMasterEntity;
	}

}
