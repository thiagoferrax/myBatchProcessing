package com.thiagoferraz.myBatchProcessing.configurations;

import com.thiagoferraz.myBatchProcessing.entities.Payment;
import com.thiagoferraz.myBatchProcessing.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class BatchConfiguration {
    private PaymentRepository paymentRepository;

    @Bean
    public FlatFileItemReader<Payment> reader() {
        FlatFileItemReader<Payment> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("payments.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setStrict(false);
        itemReader.setLineMapper(createLineMapper());
        return itemReader;
    }
    public LineMapper<Payment> createLineMapper(){
        DefaultLineMapper<Payment> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","payer_name","amount","invoice_number","additional_info");

        BeanWrapperFieldSetMapper<Payment> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Payment.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    public PaymentProcessor processor(){
        return new PaymentProcessor();
    }

    @Bean
    public RepositoryItemWriter<Payment> writer() {
        RepositoryItemWriter<Payment> writer = new RepositoryItemWriter<>();
        writer.setRepository(paymentRepository);
        return writer;
    }

    @Bean
    public Step step1(JobRepository jobRepository) {
        return new StepBuilder("import-step", jobRepository)
                .<Payment, Payment>chunk(10, transactionManager())
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    @Autowired
    public Job runJob(JobRepository jobRepository) {
        return new JobBuilder("import-payments", jobRepository)
                .start(step1(jobRepository))
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        return taskExecutor;
    }
}
