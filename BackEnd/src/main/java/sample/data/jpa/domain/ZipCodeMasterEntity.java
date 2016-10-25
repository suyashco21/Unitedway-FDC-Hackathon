package sample.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ZIPCODE_MASTER")
public class ZipCodeMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6415289531956517088L;

	@Id
	@GeneratedValue
	@Column(name = "ZIPCODE_ID", unique = true, length = 100, nullable = false)
	private Long id;

	@Column(name = "ZIPCODE_NAME", length = 100, nullable = false)
	private String zipCode;

	@ManyToOne
	@JoinColumn(name = "COUNTY_ID")
	private CountyMasterEntity countyMasterEntity;
	
	

	public ZipCodeMasterEntity() {
		super();
	}

	public ZipCodeMasterEntity(String zipCode, CountyMasterEntity countyMasterEntity) {
		super();
		this.zipCode = zipCode;
		this.countyMasterEntity = countyMasterEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public CountyMasterEntity getCountyMasterEntity() {
		return countyMasterEntity;
	}

	public void setCountyMasterEntity(CountyMasterEntity countyMasterEntity) {
		this.countyMasterEntity = countyMasterEntity;
	}

	

}
