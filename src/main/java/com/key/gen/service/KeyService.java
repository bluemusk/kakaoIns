package com.key.gen.service;

import com.key.gen.domain.KeyDomain;
import com.key.gen.repository.KeyRepository;
import com.key.gen.repository.MemoryKeyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class KeyService {
    private final KeyRepository keyRepository = new MemoryKeyRepository();
    private static double genNum = 0;

    // 키 정보 Save
    public List<KeyDomain> saveData(KeyDomain keyDomain){
        // 입력된 Key를 통해 중복 체크
        Boolean chkDup = keyRepository.checkDataByKey(keyDomain.getKey())
                                            .isPresent();

        // 중복이 존재할 경우 예외발생
        if(chkDup){
            throw new IllegalStateException("이미 존재하는 Key 정보 입니다.");
        }
        return keyRepository.saveData(keyDomain);
    }

    // 모든 Key정보 조회
    public List<KeyDomain> selectAllKey(){
        return keyRepository.selectAllKey();
    }

    // Key정보를 통하여 데이터 조회
    public Optional<KeyDomain> checkDataByKey(String key){
        return keyRepository.checkDataByKey(key);
    }

    // Key생성
    public String generateKey(String keyIn){
        Optional<KeyDomain> keyData = keyRepository.checkDataByKey(keyIn);

        // 입력된 Key정보를 통해 조회 후 존재하지 않을 경우 예외 발생
        if(keyData.isEmpty()){
            throw new IllegalStateException("(" + keyIn + ") Key 시스템에 정보 등록을 하세요.");
        }
        // 입력된 Key정보에서 Type조회회
        String type = keyData.get().getType();
        Integer minLength = keyData.get().getMinLength();
        //생성된 Key 값
        StringBuffer textKey = new StringBuffer();
        //랜덤 Key생성을 위한 선언
        Random rnd = new Random();

        // 생성될 Key Type이 string일 경우 대문자+숫자로 이루어진 4자리 문자열이 dash(-)로 4개 연결 된 형태
        if("string".equals(type)){
            for(int j = 0; j < 4; j++) {
                StringBuffer temp = new StringBuffer();      //최종 생성된 Key값을 전달하기 전 임시 저장
                for (int i = 0; i < 4; i++) {
                    int rIndex = rnd.nextInt(2);
                    switch (rIndex) {
                        case 0:
                            // A-Z
                            temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                            break;
                        case 1:
                            // 0-9
                            temp.append((rnd.nextInt(10)));
                            break;
                    }
                }
                if (j == 0) {
                    textKey.append(temp);
                } else {
                    textKey.append("-");
                    textKey.append(temp);
                }
            }
        // 생성될 Key Type이 number일 경우 는 최소 자릿수(min-length) 를 가지는 ‘정수'
        }else{
            double minNum = Math.pow(10, minLength - 1); //최소자리수 첫 시작 값 세팅

            if(genNum == 0) {
                genNum = minNum;
            }else {
                genNum = genNum + 1;
            }
            StringBuffer temp = new StringBuffer();
            textKey.append((int) genNum);
        }
        return String.valueOf(textKey);
    }
}
