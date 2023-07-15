package com.ruoyi.fuhang_booking.service.impl;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.fuhang_booking.utils.BaseInformation;
import com.ruoyi.fuhang_booking.utils.HttpUrlConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fuhang_booking.mapper.FuhangBookingMapper;
import com.ruoyi.fuhang_booking.domain.FuhangBooking;
import com.ruoyi.fuhang_booking.service.IFuhangBookingService;

/**
 * 预定Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-12
 */
@Service
@Slf4j
public class FuhangBookingServiceImpl implements IFuhangBookingService 
{
    @Autowired
    private FuhangBookingMapper fuhangBookingMapper;

    /**
     * 发送短信验证码
     * @param phone
     * @param token
     * @return
     * @throws IOException
     */
    @Override
    public String sentSMSCode(String phone, String token) throws IOException {
        JSONObject params = BaseInformation.getParams();
        params.put("token",token);
        String qCode_result = HttpUrlConnectionUtil.post(BaseInformation.getHeader(), "https://63739735004701000156623a.caiyicloud.com/cyy_gatewayapi/user/pub/v3/send_verify_code", params);
        JSONObject jsonObject = JSON.parseObject(qCode_result);
        return (String) jsonObject.get("comments");
    }

    /**
     * 发送图形验证码
     *
     * @param phone
     * @return
     * @throws IOException
     */
    @Override
    public String sentGraphCode(String phone) throws IOException {
        String qCode_result = HttpUrlConnectionUtil.post(BaseInformation.getHeader(), "https://63739735004701000156623a.caiyicloud.com/cyy_gatewayapi/user/pub/v3/generate_photo_code", BaseInformation.getParams());
        JSONObject jsonObject = JSON.parseObject(qCode_result);
        JSONObject data = (JSONObject) jsonObject.get("data");
        Object baseCode = data.get("baseCode");
        return baseCode.toString();
    }

    /**
     * 查询预定
     * 
     * @param id 预定主键
     * @return 预定
     */
    @Override
    public FuhangBooking selectFuhangBookingById(Long id)
    {
        return fuhangBookingMapper.selectFuhangBookingById(id);
    }

    /**
     * 查询预定列表
     * 
     * @param fuhangBooking 预定
     * @return 预定
     */
    @Override
    public List<FuhangBooking> selectFuhangBookingList(FuhangBooking fuhangBooking)
    {
        return fuhangBookingMapper.selectFuhangBookingList(fuhangBooking);
    }

    /**
     * 新增预定
     * 
     * @param fuhangBooking 预定
     * @return 结果
     */
    @Override
    public int insertFuhangBooking(FuhangBooking fuhangBooking) throws IOException {
        //设置访问参数
        JSONObject params = BaseInformation.getParams();
        params.put("verifyCode",fuhangBooking.getSmsCode());
        params.put("openId","oK64146NUcRVw1COtSnjJ5PLQqME");
        params.remove("verifyCodeUseType");
        params.remove("messageType");
        String result = HttpUrlConnectionUtil.post(BaseInformation.getHeader(), "https://63739735004701000156623a.caiyicloud.com/cyy_gatewayapi/user/pub/v3/wx/mini/cellphone_login_or_register", params);
        JSONObject jsonObject = JSON.parseObject(result);
        JSONObject data = (JSONObject) jsonObject.get("data");
        log.info("{}",data);
        fuhangBooking.setAccessToken((String) data.get("accessToken"));
        fuhangBooking.setBizuserid((String) data.get("bizUserId"));
        fuhangBooking.setMtcuserid((String) data.get("mtcUserId"));
//        fuhangBooking.setRefreshtoken((String) data.get("refreshToken"));
        fuhangBooking.setAuthStatus("1");
        fuhangBooking.setOrderStatus("0");
        fuhangBooking.setDealStatus("0");
        return fuhangBookingMapper.insertFuhangBooking(fuhangBooking);
    }

    /**
     * 修改预定
     * 
     * @param fuhangBooking 预定
     * @return 结果
     */
    @Override
    public int updateFuhangBooking(FuhangBooking fuhangBooking)
    {
        return fuhangBookingMapper.updateFuhangBooking(fuhangBooking);
    }

    @Override
    public int deleteFuhangBookingByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteFuhangBookingById(String id) {
        return 0;
    }

    /**
     * 批量删除预定
     * 
     * @param ids 需要删除的预定主键
     * @return 结果
     */
    @Override
    public int deleteFuhangBookingByIds(Long[] ids)
    {
        return fuhangBookingMapper.deleteFuhangBookingByIds(ids);
    }

    /**
     * 删除预定信息
     * 
     * @param id 预定主键
     * @return 结果
     */
    @Override
    public int deleteFuhangBookingById(Long id)
    {
        return fuhangBookingMapper.deleteFuhangBookingById(id);
    }
}
