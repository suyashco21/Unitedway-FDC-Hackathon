package sample.data.jpa.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "CHILD_MEASURE")
public class ChildMeasureEntity implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 5607389455046827987L;

	@Id
	@GeneratedValue
	@Column(name = "CHILD_MEASURE_ID", unique = true, length = 100, nullable = false)
	private Long id;
	
/*	
    @OneToMany
    @JoinColumn(name = "CATEGORY_ID")
	private Set<CategoryEntity> categories;*/
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COUNTY_ID")
	private CountyMasterEntity countyMasterEntity;

	
	protected ChildMeasureEntity() {
	}

	public ChildMeasureEntity(Set<ChidCategoryEntity> categories, CountyMasterEntity countyMasterEntity) {
		super();
		//this.categories = categories;
		this.countyMasterEntity = countyMasterEntity;
	}
/*
	*//**
	 * @return the categories
	 *//*
	public Set<CategoryEntity> getCategories() {
		return categories;
	}

	*//**
	 * @param categories the categories to set
	 *//*
	public void setCategories(Set<CategoryEntity> categories) {
		this.categories = categories;
	}*/

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
