package sample.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "CITY_MASTER")
public class CityMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5710212554267257045L;


	@Id
	@GeneratedValue
	@Column(name = "CITY_ID", unique = true, length = 100, nullable = false)
	private Long id;

	@Column(name = "CITY_NAME", length = 100, nullable = false)
	private String cityName;

	@ManyToOne
	@JoinColumn(name = "STATE_ID")
	private StateMasterEntity stateMasterEntity;
	
	protected CityMasterEntity() {
	}

	public CityMasterEntity(String cityName, StateMasterEntity stateMasterEntity) {
		super();
		this.cityName = cityName;
		this.stateMasterEntity = stateMasterEntity;
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
	 * @return the stateMasterEntity
	 */
	public StateMasterEntity getStateMasterEntity() {
		return stateMasterEntity;
	}

	/**
	 * @param stateMasterEntity the stateMasterEntity to set
	 */
	public void setStateMasterEntity(StateMasterEntity stateMasterEntity) {
		this.stateMasterEntity = stateMasterEntity;
	}
}
