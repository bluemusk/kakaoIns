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

    @RequestMapping("api/key/register")
    @PostMapping
    public void saveData(@RequestBody KeyDomain keyDomain){
        keyService.saveData(keyDomain);
    }

    @RequestMapping("api/key/allKey")
    @GetMapping
    public List<KeyDomain> selectAllKey(){
        return keyService.selectAllKey();
    }

    @RequestMapping("api/key/{key}")
    @GetMapping
    public HashMap selectDataByKey(@PathVariable("key") String key){
        HashMap<String, String> rtnList = new HashMap<>();
        Optional<KeyDomain> tmp =  keyService.selectDataByKey(key);
//        rtnList.put("value",tmp.get().getValue());
        return rtnList;
    }
}
