package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.StateMasterEntity;

public interface StateRepository extends Repository<StateMasterEntity, Long>{
	List<StateMasterEntity> findAll();
}
