package com.mengyirunian.service.impl;

import com.google.common.collect.Lists;
import com.mengyirunian.entity.*;
import com.mengyirunian.mapper.*;
import com.mengyirunian.service.interfaces.WeixinService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.poifs.property.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Jiaxiayuan on 2018/3/20
 */
@Service
@Slf4j
public class WeixinServiceImpl implements WeixinService {

    @Autowired
    private AccessUserMapper accessUserMapper;
    @Autowired
    private OccupationUserMapper occupationUserMapper;
    @Autowired
    private CityUserMapper cityUserMapper;
    @Autowired
    private SalaryUserMapper salaryUserMapper;
    @Autowired
    private MarriageUserMapper marriageUserMapper;
    @Autowired
    private ChildUserMapper childUserMapper;

    @Override
    public boolean existOpenId(String fromUser) {
        AccessUserCriteria accessUserCriteria = new AccessUserCriteria();
        accessUserCriteria.createCriteria().andOpenidEqualTo(fromUser);
        return !CollectionUtils.isEmpty(accessUserMapper.selectByExample(accessUserCriteria));
    }

    @Override
    public boolean bindNameAndCode(String name, String code, String fromUser) {
        AccessUserCriteria accessUserCriteria = new AccessUserCriteria();
        accessUserCriteria.createCriteria().andNameEqualTo(name).andCodeEqualTo(code);
        List<AccessUser> accessUsers = accessUserMapper.selectByExample(accessUserCriteria);
        if(CollectionUtils.isEmpty(accessUsers) || accessUsers.size() > 1) {
            return false;
        }
        AccessUser accessUser = accessUsers.get(0);
        accessUser.setOpenid(fromUser);
        accessUserMapper.updateByPrimaryKey(accessUser);
        return true;
    }

    @Override
    public List<String> getOccupationByName(String name) {
        List<String> result = Lists.newArrayList();
        OccupationUserCriteria occupationUserCriteria = new OccupationUserCriteria();
        occupationUserCriteria.createCriteria().andNameEqualTo(name);
        List<OccupationUser> occupationUsers = occupationUserMapper.selectByExample(occupationUserCriteria);
        if(!CollectionUtils.isEmpty(occupationUsers)) {
            occupationUsers.forEach(occupationUser -> result.add(occupationUser.getOccupation()));
        }
        return result;
    }

    @Override
    public List<String> getCityByName(String name) {
        List<String> result = Lists.newArrayList();
        CityUserCriteria cityUserCriteria = new CityUserCriteria();
        cityUserCriteria.createCriteria().andNameEqualTo(name);
        List<CityUser> cityUsers = cityUserMapper.selectByExample(cityUserCriteria);
        if (!CollectionUtils.isEmpty(cityUsers)) {
            cityUsers.forEach(cityUser -> result.add(cityUser.getCityName()));
        }
        return result;
    }

    @Override
    public List<Integer> getSalaryByName(String name) {
        List<Integer> result = Lists.newArrayList();
        SalaryUserCriteria salaryUserCriteria = new SalaryUserCriteria();
        salaryUserCriteria.createCriteria().andNameEqualTo(name);
        List<SalaryUser> salaryUsers = salaryUserMapper.selectByExample(salaryUserCriteria);
        if(!CollectionUtils.isEmpty(salaryUsers)) {
            salaryUsers.forEach(salaryUser -> result.add(salaryUser.getSalary()));
        }
        return result;
    }

    @Override
    public List<String> getMarriageByName(String name) {
        List<String> result = Lists.newArrayList();
        MarriageUserCriteria marriageUserCriteria = new MarriageUserCriteria();
        marriageUserCriteria.createCriteria().andNameEqualTo(name);
        List<MarriageUser> marriageUsers = marriageUserMapper.selectByExample(marriageUserCriteria);
        if (!CollectionUtils.isEmpty(marriageUsers)) {
            marriageUsers.forEach(marriageUser -> result.add(marriageUser.getMarriage()));
        }
        return result;
    }

    @Override
    public List<Integer> getChildNumByName(String name) {
        List<Integer> result = Lists.newArrayList();
        ChildUserCriteria childUserCriteria = new ChildUserCriteria();
        childUserCriteria.createCriteria().andNameEqualTo(name);
        List<ChildUser> childUsers = childUserMapper.selectByExample(childUserCriteria);
        if (!CollectionUtils.isEmpty(childUsers)) {
            childUsers.forEach(childUser -> result.add(childUser.getChildNumber()));
        }
        return result;
    }

}
