package com.fastcampus.spring_batch_fastcampus.core.service;

import com.fastcampus.spring_batch_fastcampus.core.entity.Lawd;
import com.fastcampus.spring_batch_fastcampus.core.repository.LawdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class LawdService {
    private final LawdRepository lawdRepository;

    @Transactional
    public void upsert(Lawd lawd) {
        // 데이터가 존재하면 수정, 없으면 생성
        Lawd saved = lawdRepository.findByLawdCd(lawd.getLawdCd())
                .orElseGet(Lawd::new);

        saved.setLawdCd(lawd.getLawdCd());
        saved.setLawdDong(lawd.getLawdDong());
        saved.setExist(lawd.getExist());

        lawdRepository.save(saved);
    }
}
