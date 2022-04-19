package com.citalin.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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
	
	@Autowired
	JobExecutionListener firstJobListener;
	
	@Autowired
	StepExecutionListener firstStepListener;
	
	@Autowired
	ItemReader<Integer> firstItemReader;
	
	@Autowired
	ItemProcessor<Integer,Long> firstItemProcessor;
	
	@Autowired
	ItemWriter<Long> firstItemWriter;
	
	
	@Bean
	public Job firstJob()
	{
		return jobBuilderFactory.get("First Job")
		.incrementer(new RunIdIncrementer())
		.start(firstStep())
		.next(secondStep())
		.listener(firstJobListener)
		.build();
	}

	
	private Step firstStep()
	{
		return stepBuilderFactory.get("First Step")
		.tasklet(firstTasklet)
		.listener(firstStepListener)
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
	
	@Bean
	public Job secondJob()
	{
		return jobBuilderFactory.get("Second Job")
				.incrementer(new RunIdIncrementer())
				.start(firstChunkStep())				
				.build();
	}
	
	private Step firstChunkStep()
	{
		return stepBuilderFactory.get("First Chunk Step")
				.<Integer,Long>chunk(4)	
				.reader(firstItemReader)
				.processor(firstItemProcessor)
				.writer(firstItemWriter)
				.build();
	}
	
}
