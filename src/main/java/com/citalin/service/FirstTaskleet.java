package com.citalin.service;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
public class FirstTaskleet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("This is first tasklet step");
		System.out.println("Step Exec Context: " + chunkContext.getStepContext().getStepExecutionContext());
		return RepeatStatus.FINISHED;
	}

}
