package com.key.gen.repository;

import com.key.gen.domain.KeyDomain;

import java.util.*;

public class MemoryKeyRepository implements KeyRepository {

    private static List<KeyDomain> DB = new ArrayList<>();
    private static int sequence = 0;

    // Memory에 저장
    @Override
    public List<KeyDomain> saveData(KeyDomain keyDomain) {
        DB.add(new KeyDomain(keyDomain.getKey()
                , keyDomain.getDescription()
                , keyDomain.getType()
                , keyDomain.getGenerator()
                , keyDomain.getMinLength()
                , ++sequence)
        );
        return DB;
    }

    @Override
    public List<KeyDomain> selectAllKey() {
        return DB;
    }

    @Override
    public Optional<KeyDomain> checkDataByKey(String keyIn) {
        return DB.stream().filter(keyDomain -> keyDomain.getKey().equals(keyIn)).findAny();
    }

    public void clearDB(){
        DB.clear();
    }
}
