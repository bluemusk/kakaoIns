package com.key.gen.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyDomain {
    private String key;
    private String description;
    private String type;
    private String generator;
    private Integer minLength;
    private Integer seq;

    public KeyDomain(
            @JsonProperty("key") String key,
            @JsonProperty("description") String description,
            @JsonProperty("type") String type,
            @JsonProperty("generator") String generator,
            @JsonProperty("minLength") Integer minLength,
            Integer seq) {

        this.key = key;
        this.description = description;
        this.type = type;
        this.generator = generator;
        this.minLength = minLength;
        this.seq = seq;
    }

    public KeyDomain() {
        this.key = key;
        this.description = description;
        this.type = type;
        this.generator = generator;
        this.minLength = minLength;
        this.seq = seq;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
