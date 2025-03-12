package com.rks.springbootpractice.batch.simplejob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    @Qualifier("createJobForEvent")
    private Job createJobForEvent;

    @Autowired
    private JobLauncher jobLauncher;

    @PostMapping("{eventId}")
    public void consumeEventByEventId(@PathVariable Long eventId) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(createJobForEvent,
                new JobParametersBuilder()
                        .addLong("eventId", eventId)
                        .toJobParameters());
    }
}
