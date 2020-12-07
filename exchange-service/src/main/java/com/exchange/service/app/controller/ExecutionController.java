package com.exchange.service.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.service.app.pojo.Execution;
import com.exchange.service.app.service.ExecutionService;

@RestController
public class ExecutionController {

	@Autowired
	ExecutionService executionService;
	
	@GetMapping("/executions")
	public List<Execution> executions() {
		return executionService.fetchExecutions();
	}
}
