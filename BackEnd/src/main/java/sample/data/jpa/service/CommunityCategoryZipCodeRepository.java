package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.CommunityCategoryZipCodeEntity;
import sample.data.jpa.domain.ZipCodeMasterEntity;

public interface CommunityCategoryZipCodeRepository extends Repository<CommunityCategoryZipCodeEntity, Long>{

	List<CommunityCategoryZipCodeEntity> findByZipCodeMasterEntity(ZipCodeMasterEntity zipCodeMasterEntity);
	List<CommunityCategoryZipCodeEntity> findByid(long Id);
}
