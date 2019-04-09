package com.yaoch.api.module.laofu.utils.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("股票类")
public class Stock {
    @ApiModelProperty("股票名称")
    private String stockName;
    @ApiModelProperty("今日开盘价")
    private String todayOpenPrice;
    @ApiModelProperty("昨日收盘价")
    private String yesterdayClosePrice;
    @ApiModelProperty("当前价格")
    private String price;
    @ApiModelProperty("今日最高价")
    private String todayMaxPrice;
    @ApiModelProperty("今日最低价")
    private String todayMixPrice;
    @ApiModelProperty("成交股票数，按手则需除以100")
    private String dealNum;
    @ApiModelProperty("成交金额（元）")
    private String dealMoney;
    @ApiModelProperty("买一数")
    private String buy1Num;
    @ApiModelProperty("买一价")
    private String buy1Price;
    @ApiModelProperty("买二数")
    private String buy2Num;
    @ApiModelProperty("买二价")
    private String buy2Price;
    @ApiModelProperty("买三数")
    private String buy3Num;
    @ApiModelProperty("买三价")
    private String buy3Price;
    @ApiModelProperty("买四数")
    private String buy4Num;
    @ApiModelProperty("买四价")
    private String buy4Price;
    @ApiModelProperty("买五数")
    private String buy5Num;
    @ApiModelProperty("买五价")
    private String buy5Price;
    @ApiModelProperty("卖一数")
    private String sell1Num;
    @ApiModelProperty("卖一价")
    private String sell1Price;
    @ApiModelProperty("卖二数")
    private String sell2Num;
    @ApiModelProperty("卖二价")
    private String sell2Price;
    @ApiModelProperty("卖三数")
    private String sell3Num;
    @ApiModelProperty("卖三价")
    private String sell3Price;
    @ApiModelProperty("卖四数")
    private String sell4Num;
    @ApiModelProperty("卖四价")
    private String sell4Price;
    @ApiModelProperty("卖四数")
    private String sell5Num;
    @ApiModelProperty("卖五价")
    private String sell5Price;
    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("时间")
    private String time;

}
