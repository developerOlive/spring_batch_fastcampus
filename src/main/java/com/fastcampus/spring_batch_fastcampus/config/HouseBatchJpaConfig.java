package com.fastcampus.spring_batch_fastcampus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // 생성일시, 수정일시 컬럼에 자동적으로 날짜를 설정해주기 위해 활용
public class HouseBatchJpaConfig {
}
