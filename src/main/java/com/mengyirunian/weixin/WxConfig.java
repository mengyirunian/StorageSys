package com.mengyirunian.weixin;

import com.mengyirunian.constant.WxConstant;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Jiaxiayuan on 2018/3/20
 */
@Component
@Data
public class WxConfig {

    private String token;
    private String appid;
    private String appsecret;
    private String aesKey;

    public WxConfig(){
        this.token = WxConstant.WX_TOKEN;
        this.appid = WxConstant.WX_APPID;
        this.appsecret = WxConstant.WX_APP_SECRET;
        this.aesKey = WxConstant.WX_AESKEY;
    }

}
