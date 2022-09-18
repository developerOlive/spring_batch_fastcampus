package com.fastcampus.spring_batch_fastcampus.core.repository;

import com.fastcampus.spring_batch_fastcampus.core.entity.Lawd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface lawdRepository extends JpaRepository<Lawd, Long> {
}
