package com.mengyirunian.controller;

import com.google.common.collect.Lists;
import com.mengyirunian.service.interfaces.WeixinService;
import com.mengyirunian.weixin.WeixinTool;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Jiaxiayuan on 2018/3/12
 */
@RestController
@Api(description = "wxAction")
@Slf4j
@RequestMapping(value = "/wxaction")
public class WxActionController {

    @Autowired
    private WeixinTool weixinTool;
    @Autowired
    private WeixinService weixinService;

    @RequestMapping(value = "/accesswx", method = RequestMethod.GET)
    public void accessWxGet(HttpServletRequest request, HttpServletResponse response) {
        //微信服务器get传递的参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        boolean flag = weixinTool.checkSignature(timestamp, nonce, signature);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (flag) {
                out.print(echostr);
            }
        } catch (Exception e) {
            log.info("微信接入发生异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping(value = "/initMenu", method = RequestMethod.GET)
    @ResponseBody
    public String initMenu() {
        //创建一个按钮
        WxMenu.WxMenuButton tipButton = new WxMenu.WxMenuButton();
        tipButton.setType("click");
        tipButton.setName("帮助提示");
        tipButton.setKey("helpTip");
        WxMenu menu=new WxMenu();
        menu.setButtons(Lists.newArrayList(tipButton));
        try {
            weixinTool.menuCreate(menu);
        } catch (WxErrorException e) {
            log.info("微信按钮创建失败", e);
        }
        return "初始化菜单";
    }

    @RequestMapping(value = "/accesswx", method = RequestMethod.POST)
    public void accessWxPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        WxMpXmlOutTextMessage text = null;
        try {
            WxMpXmlMessage message = WxMpXmlMessage.fromXml(request.getInputStream());
            String fromUser = message.getFromUserName();
            String touser = message.getToUserName();
            String content = message.getContent();
            String msgType = message.getMsgType();
            String msg = "请输入正确的内容";
            if("event".equals(msgType)) {
                if (("click".equals(message.getEvent()) && "helpTip".equals(message.getEventKey())) || "subscribe".equals(message.getEvent())){
                    msg = "1.在操作之前请先绑定。绑定格式发送：姓名@编码@bind。\n" +
                            "2.查询信息请发送：姓名@info。\n" +
                            "3.重新发送请发送：帮助或者help。";
                }
            } else if ("text".equals(msgType)) {
                if ("帮助".equals(content) || "help".equals(content)) {
                    msg = "1.在操作之前请先绑定。绑定格式发送：姓名@编码@bind。\n" +
                            "2.查询信息请发送：姓名@info。\n" +
                            "3.重新发送请发送：帮助或者help。";
                } else if (content.endsWith("@bind") || content.endsWith("@info")) {
                    if (!weixinService.existOpenId(fromUser) && content.endsWith("@info")) {
                        msg = "您还没有绑定过，请先绑定。";
                    } else {
                        String[] arrStr = content.split("@");
                        String name = arrStr[0].trim();
                        if (content.endsWith("@bind")) {
                            String code = arrStr[1].trim();
                            boolean flag = weixinService.bindNameAndCode(name, code, fromUser);
                            msg = flag ? "绑定成功" : "该姓名与编码信息有误";
                        } else if (content.endsWith("@info")) {
                            List<String> occupation = weixinService.getOccupationByName(name);//职业
                            List<String> cities = weixinService.getCityByName(name);//城市
                            List<Integer> salary = weixinService.getSalaryByName(name);//工资
                            List<String> marriages = weixinService.getMarriageByName(name);//婚姻
                            List<Integer> childnum = weixinService.getChildNumByName(name);//子女
                            msg = "查询结果如下：\n" + name + "\n职业：";
                            if(CollectionUtils.isEmpty(occupation)) {
                                msg += "未知\n";
                            } else {
                                for(String str : occupation) {
                                    msg += str + ",";
                                }
                                msg = msg.substring(0, msg.length() - 1);
                                msg += "\n";
                            }
                            msg += "城市：";
                            if(CollectionUtils.isEmpty(cities)) {
                                msg += "未知\n";
                            } else {
                                for(String str : cities) {
                                    msg += str;
                                    msg += ",";
                                }
                                msg = msg.substring(0, msg.length() - 1);
                                msg += "\n";
                            }
                            msg += "年收入：";
                            if(CollectionUtils.isEmpty(salary)) {
                                msg += "未知\n";
                            } else {
                                for(Integer sal : salary) {
                                    msg += sal + ",";
                                }
                                msg = msg.substring(0, msg.length() - 1);
                                msg += "\n";
                            }
                            msg += "婚姻：";
                            if(CollectionUtils.isEmpty(marriages)) {
                                msg += "未知\n";
                            } else {
                                for(String st : marriages) {
                                    msg += st;
                                    msg = msg + ",";
                                }
                                msg = msg.substring(0, msg.length() - 1);
                                msg += "\n";
                            }
                            msg += "子女个数：";
                            if(CollectionUtils.isEmpty(childnum)) {
                                msg += "未知\n";
                            } else {
                                for(Integer inte : childnum) {
                                    msg += inte;
                                    msg += ",";
                                }
                                msg = msg.substring(0, msg.length() - 1);
                                msg += "\n";
                            }
                        }
                    }
                }
            }
            text = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content(msg)
                    .build();
            out = response.getWriter();
            out.print(text.toXml());
        } catch (Exception e) {
            log.info("接收消息异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
