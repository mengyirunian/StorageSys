package com.mengyirunian.controller;

import com.google.common.collect.Sets;
import com.mengyirunian.annotation.FuncAction;
import com.mengyirunian.annotation.FuncParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@RestController
@Api(description = "index")
@Slf4j
@FuncParent(id = "indexController", name = "index说明")
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @FuncAction(parent = "indexController", name = "跳转index")
    public String index() {
        return "indez";
    }

    @ApiOperation("返回字符串")
    @RequestMapping(value = "/returnString", method = RequestMethod.GET)
    @FuncAction(parent = "indexController", name = "返回字符串")
    public String returnString() {
        log.info("接收到此请求");
        return "returnString";
    }

    @ApiOperation("获取全部的url")
    @RequestMapping(value = "/getAllUrl", method = RequestMethod.GET)
    @FuncAction(parent = "indexController", name = "获取全部的url")
    @ResponseBody
    public Set<String> getAllUrl(HttpServletRequest request) {
        Set<String> result = Sets.newHashSet();
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            result.addAll(pSet);
        }
        return result;
    }

}
