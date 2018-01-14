package com.mengyirunian.mapper;

import com.mengyirunian.entity.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiaxiayuan on 2018/1/14
 */
public interface ResourceExtMapper {

    void batchSave(Map<String, List<Resource>> map);

}
