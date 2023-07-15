package com.ruoyi.fuhang_booking.service;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.fuhang_booking.domain.FuhangBooking;

/**
 * 预定Service接口
 *
 * @date 2023-07-12
 */
public interface IFuhangBookingService
{
    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    public String sentSMSCode(String phone,String token) throws IOException;
    /**
     * 发送图形验证码
     *
     * @param phone
     * @return
     */
    public String sentGraphCode(String phone) throws IOException;
    /**
     * 查询预定
     * 
     * @param id 预定主键
     * @return 预定
     */
    public FuhangBooking selectFuhangBookingById(Long id);

    /**
     * 查询预定列表
     * 
     * @param fuhangBooking 预定
     * @return 预定集合
     */
    public List<FuhangBooking> selectFuhangBookingList(FuhangBooking fuhangBooking);

    /**
     * 新增预定
     * 
     * @param fuhangBooking 预定
     * @return 结果
     */
    public int insertFuhangBooking(FuhangBooking fuhangBooking) throws IOException;

    /**
     * 修改预定
     * 
     * @param fuhangBooking 预定
     * @return 结果
     */
    public int updateFuhangBooking(FuhangBooking fuhangBooking);

    /**
     * 批量删除预定
     * 
     * @param ids 需要删除的预定主键集合
     * @return 结果
     */
    public int deleteFuhangBookingByIds(String[] ids);

    /**
     * 删除预定信息
     * 
     * @param id 预定主键
     * @return 结果
     */
    public int deleteFuhangBookingById(String id);

    int deleteFuhangBookingById(Long id);

    int deleteFuhangBookingByIds(Long[] ids);
}
