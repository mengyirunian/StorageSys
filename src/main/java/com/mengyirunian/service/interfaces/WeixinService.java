package com.mengyirunian.service.interfaces;

import java.util.List;

/**
 * Created by Jiaxiayuan on 2018/3/20
 */
public interface WeixinService {

    boolean existOpenId(String fromUser);

    boolean bindNameAndCode(String name, String code, String fromUser);

    List<String> getOccupationByName(String name);

    List<String> getCityByName(String name);

    List<Integer> getSalaryByName(String name);

    List<String> getMarriageByName(String name);

    List<Integer> getChildNumByName(String name);
}
