package com.rks.springbootpractice.batch.simplejob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CovenantTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        Long eventId = (Long) chunkContext.getStepContext().getJobParameters().get("eventId");
        Long jobId = chunkContext.getStepContext().getStepExecution().getJobExecutionId();
        log.info("Fetch covenant data for eventId : {} and jobId : {}", eventId, jobId);
        return RepeatStatus.FINISHED;
    }
}
