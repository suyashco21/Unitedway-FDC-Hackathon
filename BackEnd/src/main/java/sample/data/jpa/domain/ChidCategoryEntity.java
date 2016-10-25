package sample.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "CHILD_MEASURE_CATEGORY_MASTER")
public class ChidCategoryEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051002970596424061L;

	@Id
	@GeneratedValue
	@Column(name = "CHILD_CATEGORY_ID", unique = true, length = 100, nullable = false)
	private Long id;
	
	@Column(name = "CHILD_CATEGORY_NAME", length = 100, nullable = false)
	private String categoryName;
	
	@Column(name = "PERCENTAGE", length = 100, nullable = false)
	private String percentage;

	@ManyToOne
	@JoinColumn(name = "COUNTY_ID")
	private CountyMasterEntity countyMasterEntity;
	
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
