package com.rks.springbootpractice.batch.simplejob;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EventConsumerConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    private FacilityTasklet facilityTasklet;
    private CollateralTasklet collateralTasklet;
    private CovenantTasklet covenantTasklet;
    private GenerateTermTasklet generateTermTasklet;

    @Bean
    public Job createJobForEvent() {
        return jobBuilderFactory.get("consume-event")
                .incrementer(new RunIdIncrementer())
                .start(facilityStep())
                .next(collateralStep())
                .next(covenantStep())
                .next(generateTermStep())
                .build();
    }

    @Bean
    public Step facilityStep() {
        return stepBuilderFactory.get("facility-step")
                .tasklet(facilityTasklet)
                .build();
    }

    @Bean
    public Step collateralStep() {
        return stepBuilderFactory.get("collateral-step")
                .tasklet(collateralTasklet)
                .build();
    }

    @Bean
    public Step covenantStep() {
        return stepBuilderFactory.get("collateral-step")
                .tasklet(covenantTasklet)
                .build();
    }

    @Bean
    public Step generateTermStep() {
        return stepBuilderFactory.get("collateral-step")
                .tasklet(generateTermTasklet)
                .build();
    }
}
