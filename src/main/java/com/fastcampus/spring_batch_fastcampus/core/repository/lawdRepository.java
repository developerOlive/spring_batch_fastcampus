package com.fastcampus.spring_batch_fastcampus.core.repository;

import com.fastcampus.spring_batch_fastcampus.core.entity.Lawd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LawdRepository extends JpaRepository<Lawd, Long> {

    Optional<Lawd> findByLawdCd(String lawdCd);
}
