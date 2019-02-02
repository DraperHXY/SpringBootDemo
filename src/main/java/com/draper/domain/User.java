package com.draper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {

    @JsonIgnore
    private Long id;

    private String name;
    private Integer age;

}
