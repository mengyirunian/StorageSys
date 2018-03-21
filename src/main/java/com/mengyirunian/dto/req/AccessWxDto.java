package com.mengyirunian.dto.req;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by Jiaxiayuan on 2018/3/12
 */
@Data
@Accessors(chain = true)
@ToString
public class AccessWxDto implements Serializable{

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

}
