package com.citalin.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("firstTaskleet")
	Tasklet firstTasklet;
	
	@Autowired
	@Qualifier("secondTasklet")
	Tasklet secondTasklet;
	
	
	
	@Bean
	public Job firstJob()
	{
		return jobBuilderFactory.get("First Job")
		.start(firstStep())
		.next(secondStep())
		.build();
	}

	
	private Step firstStep()
	{
		return stepBuilderFactory.get("First Step")
		.tasklet(firstTasklet)
		.build();
	}
	
//	private Tasklet firstTask()
//	{
//		return new Tasklet() {
//			
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				System.out.println("This is first tasklet step");
//				return RepeatStatus.FINISHED;
//			}
//		};
//	}
	
	private Step secondStep()
	{
		return stepBuilderFactory.get("Second Step")
		.tasklet(secondTasklet)
		.build();
	}
	
//	private Tasklet secondTask()
//	{
//		return new Tasklet() {
//			
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				System.out.println("This is second tasklet step");
//				return RepeatStatus.FINISHED;
//			}
//		};
//	}
}
