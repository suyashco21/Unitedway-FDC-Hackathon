package sample.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "FAMILY_MEASURE_ZIPCODE_CATEGORY_MASTER")
public class FamilyCategoryZipCodeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051002970596424061L;

	@Id
	@GeneratedValue
	@Column(name = "FAMILY_CATEGORY_ID", unique = true, length = 100, nullable = false)
	private Long id;
	
	@Column(name = "FAMILY_CATEGORY_NAME", length = 100, nullable = false)
	private String categoryName;
	
	@Column(name = "PERCENTAGE", length = 100, nullable = false)
	private String percentage;

	@ManyToOne
	@JoinColumn(name = "ZIPCODE_ID")
	private ZipCodeMasterEntity zipCodeMasterEntity;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public ZipCodeMasterEntity getZipCodeMasterEntity() {
		return zipCodeMasterEntity;
	}

	public void setZipCodeMasterEntity(ZipCodeMasterEntity zipCodeMasterEntity) {
		this.zipCodeMasterEntity = zipCodeMasterEntity;
	}

	
	
}
