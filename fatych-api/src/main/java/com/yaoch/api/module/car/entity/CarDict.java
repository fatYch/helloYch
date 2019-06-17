package com.yaoch.api.module.car.entity;

import lombok.Data;

@Data
public class CarDict {

    private String first;
    private String second;
    private String result;

    public CarDict(String first,String second,String result){
        this.first = first;
        this.second = second;
        this.result = result;
    }
}
