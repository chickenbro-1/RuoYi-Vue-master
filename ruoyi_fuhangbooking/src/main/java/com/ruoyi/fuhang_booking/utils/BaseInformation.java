package com.ruoyi.fuhang_booking.utils;

import com.alibaba.fastjson.JSONObject;

public class BaseInformation {
    final static String USER_AGENT = "user-agent";
    final static String USER_AGENT_ARG = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF XWEB/8259";
    final static String REFERER = "referer";
    final static String REFERER_ARG = "https://servicewechat.com/wx459c905b59f92c86/11/page-frame.html";
    final static String CONTENT_TYPE = "Content-Type";
    final static String CONTENT_TYPE_ARG ="application/json";
    final static String merchantId = "63739735004701000156623a";
    final static String ver = "3.2.21";
    final static String channelId = "";
    final static String src = "weixin_mini";
    final static String appId = "wx459c905b59f92c86";
    final static String cellphone = "15926440545";
    final static String verifyCodeUseType = "USER_LOGIN";
    final static String messageType = "MOBILE";

    // 生成请求头
    public static JSONObject getHeader(){
        JSONObject headers = new JSONObject();
        headers.put(BaseInformation.REFERER, BaseInformation.REFERER_ARG);
        headers.put(BaseInformation.USER_AGENT,BaseInformation.USER_AGENT_ARG );
        headers.put(BaseInformation.CONTENT_TYPE,BaseInformation.CONTENT_TYPE_ARG);
        return headers;
    }
    public static JSONObject getParams(){
        JSONObject params = new JSONObject();
        params.put("src",BaseInformation.src);
        params.put("verifyCodeUseType",BaseInformation.verifyCodeUseType);
        params.put("messageType",BaseInformation.messageType);
        params.put("ver",BaseInformation.ver);
        params.put("channelId",BaseInformation.channelId);
        params.put("appId",BaseInformation.appId);
        params.put("cellphone",BaseInformation.cellphone);
        params.put("merchantId",BaseInformation.merchantId);
        return params;
    }


}
