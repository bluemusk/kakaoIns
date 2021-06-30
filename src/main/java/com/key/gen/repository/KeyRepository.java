package com.key.gen.repository;

import com.key.gen.domain.KeyDomain;

import java.util.List;
import java.util.Optional;

public interface KeyRepository {
    List<KeyDomain> saveData(KeyDomain keyDomain);
    List<KeyDomain> selectAllKey();
    Optional<KeyDomain> checkDataByKey(String key);
}
