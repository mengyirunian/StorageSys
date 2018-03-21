package com.mengyirunian.weixin;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Jiaxiayuan on 2018/3/12
 */
@Slf4j
@Service
public class WeixinTool extends WxMpServiceImpl {

    @Autowired
    private WxConfig wxConfig;

    @PostConstruct
    public void init() {
        final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(this.wxConfig.getAppid());// 设置微信公众号的appid
        config.setSecret(this.wxConfig.getAppsecret());// 设置微信公众号的app corpSecret
        config.setToken(this.wxConfig.getToken());// 设置微信公众号的token
        config.setAesKey(this.wxConfig.getAesKey());// 设置消息加解密密钥
        super.setWxMpConfigStorage(config);

    }

}
