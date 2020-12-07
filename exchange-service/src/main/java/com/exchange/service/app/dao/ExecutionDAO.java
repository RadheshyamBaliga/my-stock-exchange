package com.exchange.service.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.exchange.service.app.pojo.Execution;

@Repository
public class ExecutionDAO {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Execution> fetchExecutions() {
		List<Execution> execs = mongoTemplate.findAll(Execution.class);
		return execs;
	}

}
