package com.key.gen.repository;

import com.key.gen.domain.KeyDomain;
import com.key.gen.service.KeyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// Repository Test
public class MemoryKeyRepositoryTest {
    KeyService keyService;
    MemoryKeyRepository repository = new MemoryKeyRepository();
    KeyDomain keyDomain = new KeyDomain();

    @AfterEach
    public void afterEach(){
        repository.clearDB();
    }

    @Test
    public void saveData(){
        keyDomain.setKey("policy-number");
        keyDomain.setDescription("보험 증서 번호에 사용할 KEY 값으로 테이블 PK 로 사용");
        keyDomain.setGenerator("mysql");
        keyDomain.setType("number");
        keyDomain.setMinLength(2);

        repository.saveData(keyDomain);
        repository.saveData(keyDomain);
        repository.saveData(keyDomain);
        repository.saveData(keyDomain);

        keyDomain = new KeyDomain();

        keyDomain.setKey("claim-number");
        keyDomain.setDescription("고객센터에서 고객 문의사항이 접수될 때 사용하는 KEY");
        keyDomain.setType("string");

        repository.saveData(keyDomain);
        repository.saveData(keyDomain);
        repository.saveData(keyDomain);
        repository.saveData(keyDomain);

        List<KeyDomain> result = repository.selectAllKey();
        Assertions.assertThat(result.size()).isEqualTo(8);
    }
}
