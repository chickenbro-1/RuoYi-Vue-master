package com.ruoyi.fuhang_booking.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;



public class FuhangBooking extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 手机号 */
    private String phone;

    /** 时间(年月日星期小时) */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT+8")
    private Date time;

    /** 票种(0:普通 1:前区) */
    private String ticketType;

    /** 预定数量 */
    private Long number;

    /** 预约授权(0:未授权 1:已授权) */
    private String authStatus;

    /** 任务状态(0:未开始 1:任务失败 2: 未支付已下单 3:已支付) */
    private String orderStatus;

    /** 成交状态(0:未成交 1:已成交) */
    private String dealStatus;

    /** 订单编号 */
    private String orderId;

    /** 转赠手机号 */
    private String transferPhone;

    /** $column.columnComment */
    private String accessToken;

    /** $column.columnComment */
    private String bizuserid;

    /** $column.columnComment */
    private String refreshtoken;

    /** $column.columnComment */
    private String mtcuserid;
    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }

    public Date getTime()
    {
        return time;
    }
    public void setTicketType(String ticketType)
    {
        this.ticketType = ticketType;
    }

    public String getTicketType()
    {
        return ticketType;
    }
    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Long getNumber()
    {
        return number;
    }
    public void setAuthStatus(String authStatus)
    {
        this.authStatus = authStatus;
    }

    public String getAuthStatus()
    {
        return authStatus;
    }
    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }
    public void setDealStatus(String dealStatus)
    {
        this.dealStatus = dealStatus;
    }

    public String getDealStatus()
    {
        return dealStatus;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setTransferPhone(String transferPhone)
    {
        this.transferPhone = transferPhone;
    }

    public String getTransferPhone()
    {
        return transferPhone;
    }
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }
    public void setBizuserid(String bizuserid)
    {
        this.bizuserid = bizuserid;
    }

    public String getBizuserid()
    {
        return bizuserid;
    }
    public void setRefreshtoken(String refreshtoken)
    {
        this.refreshtoken = refreshtoken;
    }

    public String getRefreshtoken()
    {
        return refreshtoken;
    }
    public void setMtcuserid(String mtcuserid)
    {
        this.mtcuserid = mtcuserid;
    }

    public String getMtcuserid()
    {
        return mtcuserid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("phone", getPhone())
                .append("time", getTime())
                .append("ticketType", getTicketType())
                .append("number", getNumber())
                .append("authStatus", getAuthStatus())
                .append("orderStatus", getOrderStatus())
                .append("dealStatus", getDealStatus())
                .append("orderId", getOrderId())
                .append("transferPhone", getTransferPhone())
                .append("accessToken", getAccessToken())
                .append("bizuserid", getBizuserid())
                .append("refreshtoken", getRefreshtoken())
                .append("mtcuserid", getMtcuserid())
                .toString();
    }
}
