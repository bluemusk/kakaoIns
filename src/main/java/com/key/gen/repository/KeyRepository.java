package com.key.gen.repository;

import com.key.gen.domain.KeyDomain;

import java.util.List;
import java.util.Optional;

// 인터페이스를 통한 다형성 확보
public interface KeyRepository {
    List<KeyDomain> saveData(KeyDomain keyDomain);
    List<KeyDomain> selectAllKey();
    Optional<KeyDomain> checkDataByKey(String key);
}
