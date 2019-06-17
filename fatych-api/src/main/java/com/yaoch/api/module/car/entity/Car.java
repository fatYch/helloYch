package com.yaoch.api.module.car.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("猫")
public class Car {
    @ApiModelProperty("花纹")
    private String pattern;
    @ApiModelProperty("肚子")
    private String belly;
    @ApiModelProperty("尾巴")
    private String tail;
    @ApiModelProperty("耳朵")
    private String ear;
    @ApiModelProperty("眼睛")
    private String eye;
    @ApiModelProperty("嘴巴")
    private String mouth;
    @ApiModelProperty("胡须")
    private String beard;


    private List<String> good;
}
