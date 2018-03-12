package com.mengyirunian.service.impl;

import com.mengyirunian.constant.WxConstant;
import com.mengyirunian.dto.req.AccessWxDto;
import com.mengyirunian.service.interfaces.WeixinService;
import com.mengyirunian.utils.CryptoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Jiaxiayuan on 2018/3/12
 */
@Slf4j
@Service
public class WeixinServiceImpl implements WeixinService {

    @Override
    public boolean checkSignature(AccessWxDto accessWxDto) {
        if (Objects.isNull(accessWxDto)) {
            return false;
        }
        if (StringUtils.isEmpty(accessWxDto.getEchostr()) || StringUtils.isEmpty(accessWxDto.getNonce()) || StringUtils.isEmpty(accessWxDto.getSignature()) || StringUtils.isEmpty(accessWxDto.getTimestamp())) {
            return false;
        }
        //1、排序
        String[] arr = new String[]{WxConstant.WX_TOKEN, accessWxDto.getTimestamp(), accessWxDto.getNonce()};
        Arrays.sort(arr);
        //2、生成新的字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //3、shal加密
        String temp = CryptoUtils.encodeSHA(content.toString());
        return temp.equals(accessWxDto.getTimestamp());
    }

}
