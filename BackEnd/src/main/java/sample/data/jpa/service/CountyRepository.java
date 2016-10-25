package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.CityMasterEntity;
import sample.data.jpa.domain.CountyMasterEntity;

public interface CountyRepository extends Repository<CountyMasterEntity, Long>{
	
	//@Query("select h.countyName "
	//		+ "from COUNTY_MASTER h left outer join h.stateMasterEntity r where h.stateMasterEntity = ?1 group by h")
	List<CountyMasterEntity> findByCityMasterEntity(CityMasterEntity cityMasterEntity);
	CountyMasterEntity findByCountyName(String county);
}
