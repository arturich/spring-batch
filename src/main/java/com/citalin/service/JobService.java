package com.citalin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.citalin.entity.JobParamsRequestEntity;

@Service
public class JobService {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("firstJob")
	Job firstJob;
	
	@Autowired
	@Qualifier("secondJob")
	Job secondJob;
	
	@Async
	public void startJob(String jobName, List<JobParamsRequestEntity> jobParamsRequestEntity)
	{
		Map<String, JobParameter> params = new HashMap<>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		
		jobParamsRequestEntity.stream()
		.forEach(jbp -> params.put(jbp.getParamKey(), 
				new JobParameter(jbp.getParamValue())));
		
		JobParameters jobParameters = new JobParameters(params);
		
		try {
			JobExecution jobExecution = null;
			if(jobName.equals("First Job"))
			{
				jobExecution = jobLauncher.run(firstJob, jobParameters);
			} else if(jobName.equals("Second Job"))
			{
				jobExecution = jobLauncher.run(secondJob, jobParameters);
			}
			System.out.println("JobExecution id = " + jobExecution.getId());
		} catch (Exception e) {
			System.out.println("Exception while starting job");
		}
		
	}

}
