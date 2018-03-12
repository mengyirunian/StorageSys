package com.mengyirunian.controller;

import com.mengyirunian.dto.req.AccessWxDto;
import com.mengyirunian.service.interfaces.WeixinService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jiaxiayuan on 2018/3/12
 */
@RestController
@Api(description = "accesswx")
@Slf4j
public class AccessWxController {

    @Autowired
    private WeixinService weixinService;

    @RequestMapping(value = "/accesswx", method = RequestMethod.GET)
    public void accessWx(@RequestBody AccessWxDto accessWxDto, HttpServletResponse response) {
        boolean flag = weixinService.checkSignature(accessWxDto);
        PrintWriter p;
        try {
            p = response.getWriter();
            if (flag) {
                p.print(accessWxDto.getEchostr());//注意此处必须返回echostr以完成验证
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
