package com.yaoch.api.module.laofu.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yaoch.api.module.car.entity.Car;
import com.yaoch.api.module.car.entity.CarDict;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 猫基因配对
 */
public class CarUtil {

    private final static String PATTERN = "pattern";
    private final static String BELLY = "belly";
    private final static String TAIL = "tail";
    private final static String EAR = "ear";
    private final static String EYE = "eye";
    private final static String MOUTH = "mouth";
    private final static String BEARD = "beard";
    private static HashMap<String,List<CarDict>> dicts = Maps.newHashMap();

    static {
        //花纹
        putCarDict(PATTERN,"阿尔卑斯","草莓饼干","生如夏花");
        putCarDict(PATTERN,"虎斑","美人尖","洋葱面具");
        putCarDict(PATTERN,"力量印记","雨花台","爱的魔法");
        putCarDict(PATTERN,"螺旋封印","凯撒","一叶障目");
        //肚子
        putCarDict(BELLY,"钻石甜心","小猫秒盗","超能蒙面");
        putCarDict(BELLY,"太空战士","肌肉战士","煤堆探索");
        //尾巴
        //耳朵
        putCarDict(EAR,"小尖尖","竖耳","小毛毛");
        //眼睛
        putCarDict(EYE,"死鱼眼","街头霸喵","童话萌喵");
        putCarDict(EYE,"怒发冲冠","呆萌宝宝","好怕怕");
        putCarDict(EYE,"夜猫眼","眯眯眼","海蓝宝石");
        putCarDict(EYE,"专心致志","俏皮甜心","圆点点");
        putCarDict(EYE,"街头霸喵","砰然惊喜","闭目养神");
        putCarDict(EYE,"闭目养神","确认眼神","小可怜");
        //嘴巴
        putCarDict(MOUTH,"咬牙切齿","开心派","傻乎乎");
        putCarDict(MOUTH,"一阵沉默","愤怒","开心派");
        putCarDict(MOUTH,"胖嘟嘟","迷三角","咬牙切齿");
        putCarDict(MOUTH,"欢乐派","可爱虎牙","流口水");
        //胡须
        putCarDict(BEARD,"流口水","欢乐派","小馋猫");
    }

    public static void putCarDict(String type,String a,String b,String c){
        List<CarDict> carDicts = dicts.get(type);
        if(CollectionUtils.isEmpty(carDicts)){
            carDicts = Lists.newArrayList();
            carDicts.add(new CarDict(a,b,c));
            dicts.put(type,carDicts);
            return;
        }
        carDicts.add(new CarDict(a,b,c));
    }

    public static Car getGoodCar(Car car){
        Car result = new Car();
        List<String> good = Lists.newArrayList();
        //遍历花纹
        for(CarDict carDict:dicts.get(PATTERN)){
            if(carDict.getFirst().equals(car.getPattern()) ||carDict.getSecond().equals(car.getPattern())){
                result.setPattern(carDict.getFirst().equals(car.getPattern())? carDict.getSecond() : carDict.getFirst());
                good.add(carDict.getResult());
            }
        }
        for(CarDict carDict:dicts.get(BELLY)){
            if(carDict.getFirst().equals(car.getBelly()) ||carDict.getSecond().equals(car.getBelly())){
                result.setBelly(carDict.getFirst().equals(car.getBelly())? carDict.getSecond() : carDict.getFirst());
                good.add(carDict.getResult());
            }
        }
//        for(CarDict carDict:dicts.get(TAIL)){
//            if(carDict.getFirst().equals(car.getTail()) ||carDict.getSecond().equals(car.getTail())){
//                result.setTail(carDict.getFirst().equals(car.getTail())? carDict.getSecond() : carDict.getFirst());
//                good.add(carDict.getResult());
//            }
//        }
        for(CarDict carDict:dicts.get(EYE)){
            if(carDict.getFirst().equals(car.getEye()) ||carDict.getSecond().equals(car.getEye())){
                result.setEye(carDict.getFirst().equals(car.getEye())? carDict.getSecond() : carDict.getFirst());
                good.add(carDict.getResult());
            }
        }
        for(CarDict carDict:dicts.get(EAR)){
            if(carDict.getFirst().equals(car.getEar()) ||carDict.getSecond().equals(car.getEar())){
                result.setEar(carDict.getFirst().equals(car.getEar())? carDict.getSecond() : carDict.getFirst());
                good.add(carDict.getResult());
            }
        }
        for(CarDict carDict:dicts.get(BEARD)){
            if(carDict.getFirst().equals(car.getBeard()) ||carDict.getSecond().equals(car.getBeard())){
                result.setBeard(carDict.getFirst().equals(car.getBeard())? carDict.getSecond() : carDict.getFirst());
                good.add(carDict.getResult());
            }
        }
        for(CarDict carDict:dicts.get(MOUTH)){
            if(carDict.getFirst().equals(car.getMouth()) ||carDict.getSecond().equals(car.getMouth())){
                result.setMouth(carDict.getFirst().equals(car.getMouth())? carDict.getSecond() : carDict.getFirst());
                good.add(carDict.getResult());
            }
        }
        result.setGood(good);
        return result;
    }


    public static void main(String[] args){
        Car car = new Car();
        car.setEar("竖耳");
        car.setPattern("力量印记");
        car.setEye("夜猫眼");
        System.out.println(JSON.toJSON(getGoodCar(car)));
    }

}
