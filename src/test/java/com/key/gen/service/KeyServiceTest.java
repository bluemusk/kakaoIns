package com.key.gen.service;

import com.key.gen.domain.KeyDomain;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// keyService Test
class KeyServiceTest {
    KeyDomain keyDomain = new KeyDomain();
    KeyService keyService = new KeyService();

    @Test
    void saveData() {
        //데이터 Setting
        keyDomain.setKey("policy-number");
        keyDomain.setDescription("보험 증서 번호에 사용할 KEY 값으로 테이블 PK 로 사용");
        keyDomain.setGenerator("mysql");
        keyDomain.setType("number");
        keyDomain.setMinLength(2);

        //데이터 저장
        keyService.saveData(keyDomain);

        //중복 키 저장 -> Exception 발생 여부 확인
        try {
            keyService.saveData(keyDomain);
        }catch(IllegalStateException e){
            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 Key 정보 입니다.");
        }
        //데이터 초기화
        keyDomain = new KeyDomain();

        //데이터 세팅
        keyDomain.setKey("claim-number");
        keyDomain.setDescription("고객센터에서 고객 문의사항이 접수될 때 사용하는 KEY");
        keyDomain.setType("string");

        //데이터 저장
        keyService.saveData(keyDomain);

        //중복 키 저장 -> Exception 발생 여부 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> keyService.saveData(keyDomain));
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 Key 정보 입니다.");
        List<KeyDomain> result = keyService.selectAllKey();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void checkDataByKey() {
        //데이터 세팅 - policy-number
        keyDomain.setKey("policy-number");

        //데이터 저장
        keyService.saveData(keyDomain);

        //데이터 조회
        Optional<KeyDomain> result = keyService.checkDataByKey(keyDomain.getKey());

        //결과 확인
        if(result.isPresent()){
            System.out.println("성공");
        }

        //데이터 세팅 - claim-number
        keyDomain.setKey("claim-number");

        //데이터 저장 없이 데이터 조회
        Optional<KeyDomain> result2 = keyService.checkDataByKey(keyDomain.getKey());

        //결과 확인
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