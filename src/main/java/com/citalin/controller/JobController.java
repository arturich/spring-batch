package com.citalin.controller;

import java.util.List;

import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citalin.entity.JobParamsRequestEntity;
import com.citalin.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	JobOperator jobOperator;
	
	@PostMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName, 
			@RequestBody List<JobParamsRequestEntity> jobParamsRequestEntityList) throws Exception
	{
		jobService.startJob(jobName, jobParamsRequestEntityList);
		return "Job Started...";
	}	
	
	@GetMapping("/stop/{jboExecutionId}")
	public String stopJob(@PathVariable long jboExecutionId)	
	{
		try {
			jobOperator.stop(jboExecutionId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "Job Stopped...";
	}

}
