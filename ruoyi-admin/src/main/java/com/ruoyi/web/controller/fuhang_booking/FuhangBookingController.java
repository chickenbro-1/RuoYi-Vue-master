package com.ruoyi.web.controller.fuhang_booking;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.fuhang_booking.domain.FuhangBooking;
import com.ruoyi.fuhang_booking.service.IFuhangBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * bookingController
 *
 * @date 2023-07-12
 */
@RestController
@RequestMapping("/fuhang_booking")
public class FuhangBookingController extends BaseController
{
    @Autowired
    private IFuhangBookingService fuhangBookingService;

    /**
     * 获取图形验证码
     */
    @PreAuthorize("@ss.hasPermi('fuhang_booking:booking:graphCode')")
    @GetMapping(value = "/graphcode/{phone}")
    public R sentGraphCode(@PathVariable("phone") String phone) throws IOException {
        return R.ok(fuhangBookingService.sentGraphCode(phone));
    }
    /**
     * 获取 短信验证码
     */
    @PreAuthorize("@ss.hasPermi('fuhang_booking:booking:SMSCode')")
    @GetMapping(value = "/smscode/{token}/{phone}")
    public R sentSMSCode(@PathVariable("token") String token,@PathVariable("phone") String phone) throws IOException {
        String res = fuhangBookingService.sentSMSCode(phone, token);
        return R.ok(res);
    }

    /**
     * 查询booking列表
     */
    @PreAuthorize("@ss.hasPermi('fuhang_booking:booking:list')")
    @GetMapping("/booking/list")
    public TableDataInfo list(FuhangBooking fuhangBooking)
    {
        startPage();
        List<FuhangBooking> list = fuhangBookingService.selectFuhangBookingList(fuhangBooking);
        return getDataTable(list);
    }
    /**
     * 获取booking详细信息
     */
    @PreAuthorize("@ss.hasPermi('fuhang_booking:booking:query')")
    @GetMapping(value = "/booking/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fuhangBookingService.selectFuhangBookingById(id));
    }
    /**
     * 新增预约任务
     */
    @PreAuthorize("@ss.hasPermi('fuhang_booking:booking:add')")
    @Log(title = "booking", businessType = BusinessType.INSERT)
    @PostMapping("/booking")
    public AjaxResult add(@RequestBody FuhangBooking fuhangBooking) throws IOException {
        return toAjax(fuhangBookingService.insertFuhangBooking(fuhangBooking));
    }

    /**
     * 修改booking
     */
    @PreAuthorize("@ss.hasPermi('fuhang_booking:booking:edit')")
    @Log(title = "booking", businessType = BusinessType.UPDATE)
    @PutMapping("/booking")
    public AjaxResult edit(@RequestBody FuhangBooking fuhangBooking)
    {
        return toAjax(fuhangBookingService.updateFuhangBooking(fuhangBooking));
    }
    /**
     * 删除booking
     */
    @PreAuthorize("@ss.hasPermi('fuhang_booking:booking:remove')")
    @Log(title = "booking", businessType = BusinessType.DELETE)
	@DeleteMapping("/booking/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fuhangBookingService.deleteFuhangBookingByIds(ids));
    }


}
