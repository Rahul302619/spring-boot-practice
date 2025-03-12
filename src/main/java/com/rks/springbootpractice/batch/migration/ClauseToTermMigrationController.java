package com.rks.springbootpractice.batch.migration;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("clause-to-term/migration")
public class ClauseToTermMigrationController {

    private JobLauncher jobLauncher;
    private Job clauseToTermMigrationJob; // Autowire your batch job

    @PostMapping("startJob")
    public String startJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(clauseToTermMigrationJob, jobParameters);
            return "Job started successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to start job: " + e.getMessage();
        }
    }

    @PostMapping("/stopJob")
    public String stopJob() {
        // Implement logic to stop the job if necessary
        // Note: Spring Batch doesn't have built-in support for stopping jobs once they've started.
        // You might need to handle this at the job level (e.g., by setting a flag to stop processing).
        return "Job stopping functionality is not implemented";
    }
}
