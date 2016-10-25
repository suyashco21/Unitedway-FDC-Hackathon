package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.FamilyCategoryZipCodeEntity;
import sample.data.jpa.domain.ZipCodeMasterEntity;

public interface FamilyCategoryZipCodeRepository extends Repository<FamilyCategoryZipCodeEntity, Long>{

	List<FamilyCategoryZipCodeEntity> findByZipCodeMasterEntity(ZipCodeMasterEntity zipCodeMasterEntity);
	List<FamilyCategoryZipCodeEntity>  findById(long Id);
}
