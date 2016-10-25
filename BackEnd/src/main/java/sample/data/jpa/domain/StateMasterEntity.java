package sample.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "STATE_MASTER")
public class StateMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4539520430711473268L;

	@Id
	@GeneratedValue
	@Column(name = "STATE_ID", unique = true, length = 100, nullable = false)
	private Long id;

	@Column(name = "STATE_NAME", length = 100, nullable = false)
	private String stateName;

	protected StateMasterEntity() {
	}

	public StateMasterEntity(String stateName) {
		super();
		this.stateName = stateName;
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
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
