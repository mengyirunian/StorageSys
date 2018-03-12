package com.mengyirunian.service.interfaces;

import com.mengyirunian.dto.req.AccessWxDto; /**
 * Created by Jiaxiayuan on 2018/3/12
 */
public interface WeixinService {
    boolean checkSignature(AccessWxDto accessWxDto);
}
