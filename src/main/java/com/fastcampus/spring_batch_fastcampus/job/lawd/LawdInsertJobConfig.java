package com.fastcampus.spring_batch_fastcampus.job.lawd;

import com.fastcampus.spring_batch_fastcampus.core.entity.Lawd;
import com.fastcampus.spring_batch_fastcampus.core.service.LawdService;
import com.fastcampus.spring_batch_fastcampus.job.validator.FilePathParameterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import static com.fastcampus.spring_batch_fastcampus.job.lawd.LawdFiledSetMapper.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class LawdInsertJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final LawdService lawdService;

    @Bean
    public Job lawdInsertJob(Step lawdInsertStep) {
        return jobBuilderFactory.get("lawdInsertJob")
                .incrementer(new RunIdIncrementer())
                .validator(new FilePathParameterValidator())
                .start(lawdInsertStep)
                .build();
    }

    @JobScope
    @Bean
    public Step lawdInsertStep(FlatFileItemReader<Lawd> lawdFileItemReader,
                               ItemWriter<Lawd> itemWriter) {
        return stepBuilderFactory.get("lawdInsertStep")
                .<Lawd, Lawd>chunk(1000)
                .reader(lawdFileItemReader)
                .writer(itemWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Lawd> lawdFileItemReader(@Value("#{jobParameters['filePath']}") String filePath) {
        return new FlatFileItemReaderBuilder<Lawd>()
                .name("lawdFileItemReader")
                .delimited() // 구분자
                .delimiter("\t") // 각각의 필드를 탭으로 구분하겠다는 의미
                .names(LAWD_CD, LAWD_DONG, EXIST) // 텍스트 파일의 각각의 필드를 객체의 필드와 맵핑할 때 사용하는 이름
                .linesToSkip(1) // 텍스트 파일에서 첫번째 줄은 제외 (제목이기 때문)
                .fieldSetMapper(new LawdFiledSetMapper()) // 파일에 있는 필드를 각각 entity에 맵핑해주는 작업
                .resource(new ClassPathResource(filePath)) // 읽어올 파일 지정
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<Lawd> lawdItemWriter() {
        return items -> items.forEach(lawdService::upsert);
    }
}
