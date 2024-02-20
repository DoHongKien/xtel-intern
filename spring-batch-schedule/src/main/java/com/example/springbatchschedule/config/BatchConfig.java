package com.example.springbatchschedule.config;

import com.example.springbatchschedule.batch.StudentProcessor;
import com.example.springbatchschedule.batch.StudentWriter;
import com.example.springbatchschedule.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class BatchConfig {

    // Hàm job() thực thi batch
    @Bean
    public Job job(JobRepository repository, PlatformTransactionManager transaction) {
        log.info("Start job batch");
        return new JobBuilder("student-job", repository)
                .incrementer(new RunIdIncrementer())
                .start(step(repository, transaction))
                .build();
    }

    // Hàm thực hiện các bước theo thứ tự
    @Bean
    public Step step(JobRepository repository, PlatformTransactionManager transaction) {
        log.info("Start step batch");
        return new StepBuilder("student-step", repository)
                // Thiết lập cho batch xử lý 10 đối tượng 1 lần
                .<Student, Student>chunk(10, transaction)
                // Đọc dữ liệu
                .reader(reader())
                // Tiến trình xử lý dữ liệu
                .processor(processor())
                // Ghi dữ liệu
                .writer(writer())
                .build();
    }

    // Hàm reader() đọc dữ liệu từ file
    @Bean
    @StepScope
    public FlatFileItemReader<Student> reader() {
        log.info("Start reader batch");
        return new FlatFileItemReaderBuilder<Student>()
                // Đặt tên cho reader()
                .name("student-reader")
                .resource(new ClassPathResource("students.csv"))
                // Bỏ dòng đầu
                .linesToSkip(1)
                .delimited()
                //Thiết lập tên của các trường trong bảng
                .names(new String[]{"name", "age", "gender", "address", "birthday"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(Student.class);
                }})
                .build();
    }

    // Tiến trình xử lý dữ liệu
    @Bean
    public ItemProcessor<Student, Student> processor() {
        log.info("Start processor batch");
        return new StudentProcessor();
    }

    // Hàm writer() ghi dữ liệu vào database
    @Bean
    @StepScope
    public ItemWriter<Student> writer() {
        log.info("Start writer batch");
        return new StudentWriter();
    }
}
