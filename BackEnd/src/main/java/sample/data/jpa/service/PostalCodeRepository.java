package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.CountyMasterEntity;
import sample.data.jpa.domain.PostalCodeEntity;

public interface PostalCodeRepository extends Repository<PostalCodeEntity, Long>{

	List<PostalCodeEntity> findByCountyMasterEntity(CountyMasterEntity countyMasterEntity);
}
