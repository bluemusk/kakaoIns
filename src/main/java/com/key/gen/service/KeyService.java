package com.key.gen.service;

import com.key.gen.domain.KeyDomain;
import com.key.gen.repository.KeyRepository;
import com.key.gen.repository.MemoryKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class KeyService {
    private final KeyRepository keyRepository = new MemoryKeyRepository();
    private static double genNum = 0;

    public List<KeyDomain> saveData(KeyDomain keyDomain){
        Boolean chkDup = keyRepository.checkDataByKey(keyDomain.getKey())
                                            .isPresent();
        if(chkDup){
            throw new IllegalStateException("이미 존재하는 Key입니다.");
        }
        return keyRepository.saveData(keyDomain);
    }

    public List<KeyDomain> selectAllKey(){
        return keyRepository.selectAllKey();
    }

    public Optional<KeyDomain> checkDataByKey(String key){
        return keyRepository.checkDataByKey(key);
    }

    public String generateKey(String keyIn){
        Optional<KeyDomain> keyData = keyRepository.checkDataByKey(keyIn);

        if(keyData.isEmpty()){
            throw new IllegalStateException("(" + keyIn + ") Key 시스템에 정보 등록을 하세요.");
        }
        String type = keyData.get().getType();
        Integer minLength = keyData.get().getMinLength();

        StringBuffer textKey = new StringBuffer();           //생성된 Key 값
        Random rnd = new Random();                           //랜덤 Key생성을 위한 선언

        if("string".equals(type)){
            for(int j = 0; j < 4; j++) {
                StringBuffer temp = new StringBuffer();
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
        }else{
            double minNum = Math.pow(10, minLength - 1); //최소자리수

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
