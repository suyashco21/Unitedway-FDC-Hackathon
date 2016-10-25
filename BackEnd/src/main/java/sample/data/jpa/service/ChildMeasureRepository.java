package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.ChildMeasureEntity;
import sample.data.jpa.domain.CountyMasterEntity;

public interface ChildMeasureRepository extends Repository<ChildMeasureEntity, Long>{
	
	List<ChildMeasureEntity> findByCountyMasterEntity(CountyMasterEntity countyMasterEntity);
}
