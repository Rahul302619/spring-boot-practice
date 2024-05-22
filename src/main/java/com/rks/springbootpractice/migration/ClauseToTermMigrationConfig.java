package com.rks.springbootpractice.migration;

import com.rks.springbootpractice.entity.Clause;
import com.rks.springbootpractice.entity.Term;
import com.rks.springbootpractice.repository.ClauseRepo;
import com.rks.springbootpractice.repository.TermRepo;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class ClauseToTermMigrationConfig {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    private ClauseRepo clauseRepo;
    private TermRepo termRepo;

    //We can configure JobOperator for restarting the job

    @Bean
    public Job clauseToTermMigrationJob() {
        return jobBuilderFactory.get("clauseToTermMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(clauseToTermMigrationStep())
                //.next(clauseToTermMigrationStep())
                .build();
    }

    @Bean
    public Step clauseToTermMigrationStep() {
        return stepBuilderFactory.get("clauseToTermMigrationStep")
                .<Clause, Term>chunk(100)
                .reader(clauseDataReader())
                .processor(clauseToTermDataProcessor())
                .writer(termDataWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public ItemReader<Clause> clauseDataReader() {
        RepositoryItemReader<Clause> reader = new RepositoryItemReader<>();
        reader.setRepository(clauseRepo);
        reader.setMethodName("findAll");
        reader.setPageSize(100);
        HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @Bean
    public ItemProcessor<Clause, Term> clauseToTermDataProcessor() {
        return clause -> {
            Term term = new Term();
            term.setData(clause.getData());
            term.setTermType(clause.getClauseType());
            return term;
        };
    }

    @Bean
    public ItemWriter<Term> termDataWriter() {
        RepositoryItemWriter<Term> writer = new RepositoryItemWriter<>();
        writer.setRepository(termRepo);
        writer.setMethodName("saveAll");
        return writer;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Set core pool size
        executor.setCorePoolSize(10); // Number of threads to keep alive when idle

        // Set maximum pool size
        executor.setMaxPoolSize(20); // Maximum number of threads to allow in the pool

        // Set queue capacity
        executor.setQueueCapacity(100); // Maximum number of tasks that can be queued

        // Set keep alive time
        executor.setKeepAliveSeconds(60); // Maximum time that excess idle threads will wait for new tasks

        // Set thread name prefix
        executor.setThreadNamePrefix("batch-step-executor-");
        executor.initialize();

        return executor;
    }
}
