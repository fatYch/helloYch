package com.yaoch.module.account.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("账户总值")
@Data
public class AccountVo {
    private Double money;
    private List<InstrumentVo> instruments;
}
