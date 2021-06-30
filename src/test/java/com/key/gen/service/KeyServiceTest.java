package com.key.gen.service;

import com.key.gen.domain.KeyDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class KeyServiceTest {
    KeyDomain keyDomain = new KeyDomain();
    KeyService keyService = new KeyService();

    @Test
    void saveData() {
        keyDomain.setKey("policy-number");
        keyDomain.setDescription("보험 증서 번호에 사용할 KEY 값으로 테이블 PK 로 사용");
        keyDomain.setGenerator("mysql");
        keyDomain.setType("number");
        keyDomain.setMinLength(2);

        keyService.saveData(keyDomain);

        keyDomain = new KeyDomain();

        keyDomain.setKey("claim-number");
        keyDomain.setDescription("고객센터에서 고객 문의사항이 접수될 때 사용하는 KEY");
        keyDomain.setType("string");

        keyService.saveData(keyDomain);

        List<KeyDomain> result = keyService.selectAllKey();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void checkDataByKey() {
        keyDomain.setKey("policy-number");
        keyService.saveData(keyDomain);

        Optional<KeyDomain> result = keyService.checkDataByKey(keyDomain.getKey());
        if(result.isPresent()){
            System.out.println("성공");
        }

        keyDomain.setKey("claim-number");

        Optional<KeyDomain> result2 = keyService.checkDataByKey(keyDomain.getKey());
        if(result2.isEmpty()){
            System.out.println("실퍠");
        }
    }

    @Test
    void generateKey() {
        keyDomain.setKey("policy-number");
        keyDomain.setDescription("보험 증서 번호에 사용할 KEY 값으로 테이블 PK 로 사용");
        keyDomain.setGenerator("mysql");
        keyDomain.setType("number");
        keyDomain.setMinLength(2);

        keyService.saveData(keyDomain);
        System.out.println("policy-number : " + keyService.generateKey(keyDomain.getKey()));
        System.out.println("policy-number : " + keyService.generateKey(keyDomain.getKey()));
        System.out.println("policy-number : " + keyService.generateKey(keyDomain.getKey()));
        System.out.println("policy-number : " + keyService.generateKey(keyDomain.getKey()));

        keyDomain = new KeyDomain();

        keyDomain.setKey("claim-number");
        keyDomain.setDescription("고객센터에서 고객 문의사항이 접수될 때 사용하는 KEY");
        keyDomain.setType("string");

        keyService.saveData(keyDomain);

        System.out.println("claim-number : " + keyService.generateKey(keyDomain.getKey()));
        System.out.println("claim-number : " + keyService.generateKey(keyDomain.getKey()));
        System.out.println("claim-number : " + keyService.generateKey(keyDomain.getKey()));
        System.out.println("claim-number : " + keyService.generateKey(keyDomain.getKey()));
    }
}