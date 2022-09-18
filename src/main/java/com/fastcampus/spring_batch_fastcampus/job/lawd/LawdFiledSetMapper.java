package com.fastcampus.spring_batch_fastcampus.job.lawd;

import com.fastcampus.spring_batch_fastcampus.core.entity.Lawd;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class LawdFiledSetMapper implements FieldSetMapper<Lawd> {
    public static final String LAWD_CD = "lawdCd";
    public static final String LAWD_DONG = "lawdDong";
    public static final String EXIST = "exist";

    private static final String EXIST_TRUE = "존재";

    // 한 라인에 있는 필드를 읽어 Lawd entity로 변환해주는 작업
    @Override
    public Lawd mapFieldSet(FieldSet fieldSet) throws BindException {
        Lawd lawd = new Lawd();
        lawd.setLawdCd(fieldSet.readString(LAWD_CD));
        lawd.setLawdDong(fieldSet.readString(LAWD_DONG));
        lawd.setExist(fieldSet.readBoolean(EXIST, EXIST_TRUE));

        return lawd;
    }
}
