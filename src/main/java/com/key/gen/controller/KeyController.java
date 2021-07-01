package com.key.gen.controller;

import com.key.gen.domain.KeyDomain;
import com.key.gen.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class KeyController {

    private final KeyService keyService;

    @Autowired
    public KeyController(KeyService keyService) {
        this.keyService = keyService;
    }

    // api/key/register로 접근 (Key정보 저장)
    @RequestMapping("api/key/register")
    @PostMapping
    public void saveData(@RequestBody KeyDomain keyDomain){
        keyService.saveData(keyDomain);
    }

    // api/key/allKey로 접근 (모든 Key정보 조회)
    @RequestMapping("api/key/allKey")
    @GetMapping
    public List<KeyDomain> selectAllKey(){
        return keyService.selectAllKey();
    }

    // api/key/키 이름 (Key발급)
    @RequestMapping("api/key/{key}")
    @GetMapping
    public HashMap selectDataByKey(@PathVariable("key") String key){
        HashMap<String, String> rtnList = new HashMap<>();
        String tmpValue =  keyService.generateKey(key);
        rtnList.put("value",tmpValue);
        return rtnList;
    }
}
