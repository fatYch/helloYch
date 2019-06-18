package com.yaoch.module.account.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("币值")
public class InstrumentVo {
    @JSONField(name = "instrument_id")
    private String name;
    @JSONField(name = "equity")
    private Double num;
    private Double price;
}
