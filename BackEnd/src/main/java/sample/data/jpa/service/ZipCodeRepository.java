package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.CountyMasterEntity;
import sample.data.jpa.domain.ZipCodeMasterEntity;

public interface ZipCodeRepository extends Repository<ZipCodeMasterEntity, Long> {
	List<ZipCodeMasterEntity> findByCountyMasterEntity(CountyMasterEntity countyMasterEntity);
}
