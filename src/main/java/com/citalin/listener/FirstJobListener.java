package com.citalin.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {		
		System.out.println("Before job n1: " + jobExecution.getJobInstance().getJobName());
		System.out.println("Before job n2: " + jobExecution.getJobConfigurationName());
		System.out.println("Before job Params: " + jobExecution.getJobParameters());
		System.out.println("Before job Exec context: " + jobExecution.getExecutionContext());
		
		jobExecution.getExecutionContext().put("jec", "jec value");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("After job n1: " + jobExecution.getJobInstance().getJobName());
		System.out.println("After job n2: " + jobExecution.getJobConfigurationName());
		System.out.println("After job Params: " + jobExecution.getJobParameters());
		System.out.println("After job Exec context: " + jobExecution.getExecutionContext());
		
	}

}
