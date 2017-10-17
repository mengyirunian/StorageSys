package com.mengyirunian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "index")
public class IndexController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "indez";
    }

    @ApiOperation("返回字符串")
    @RequestMapping(value = "/returnString", method = RequestMethod.GET)
    public String returnString() {
        logger.info("接收到此请求");
        return "returnString";
    }

}
