package com.ms.junz.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ms.junz.domain.Request;

public interface RequestDao extends CrudRepository<Request, Long>{
	List<Request> findByName(String name);
}
