package sample.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "POSTAL_CODE_MASTER")
public class PostalCodeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6179191054885059833L;
	
	
	@Id
	@GeneratedValue
	@Column(name = "POSTAL_ID", unique = true, length = 100, nullable = false)
	private Long id;

	@Column(name = "POSTAL_NAME", length = 100, nullable = false)
	private String postalName;
	
	@Column(name = "POSTAL_CODE", length = 100, nullable = false)
	private String postalCode;
	
	@ManyToOne
	@JoinColumn(name = "COUNTY_ID")
	private CountyMasterEntity countyMasterEntity;

	protected PostalCodeEntity() {
	}

	public PostalCodeEntity(String postalCode, CountyMasterEntity countyMasterEntity) {
		super();
		this.postalCode = postalCode;
		this.countyMasterEntity = countyMasterEntity;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the postalName
	 */
	public String getPostalName() {
		return postalName;
	}

	/**
	 * @param postalName the postalName to set
	 */
	public void setPostalName(String postalName) {
		this.postalName = postalName;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the countyMasterEntity
	 */
	public CountyMasterEntity getCountyMasterEntity() {
		return countyMasterEntity;
	}

	/**
	 * @param countyMasterEntity the countyMasterEntity to set
	 */
	public void setCountyMasterEntity(CountyMasterEntity countyMasterEntity) {
		this.countyMasterEntity = countyMasterEntity;
	}

}
