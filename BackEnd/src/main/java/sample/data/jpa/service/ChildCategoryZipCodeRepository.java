package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.ChidCategoryZipCodeEntity;
import sample.data.jpa.domain.ZipCodeMasterEntity;

public interface ChildCategoryZipCodeRepository extends Repository<ChidCategoryZipCodeEntity, Long>{

	List<ChidCategoryZipCodeEntity> findByZipCodeMasterEntity(ZipCodeMasterEntity zipCodeMasterEntity);
	List<ChidCategoryZipCodeEntity> findById(long Id);
}
