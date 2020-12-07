package com.exchange.service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.service.app.dao.ExecutionDAO;
import com.exchange.service.app.pojo.Execution;

@Service
public class ExecutionService {

	@Autowired
	ExecutionDAO executionDAO;
	
	public List<Execution> fetchExecutions() {
		return executionDAO.fetchExecutions();
	}
}
