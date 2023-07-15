package com.ruoyi.fuhang_booking.mapper;
import java.util.List;
import com.ruoyi.fuhang_booking.domain.FuhangBooking;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FuhangBookingMapper
{
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
    public int insertFuhangBooking(FuhangBooking fuhangBooking);

    /**
     * 修改预定
     *
     * @param fuhangBooking 预定
     * @return 结果
     */
    public int updateFuhangBooking(FuhangBooking fuhangBooking);

    /**
     * 删除预定
     *
     * @param id 预定主键
     * @return 结果
     */
    public int deleteFuhangBookingById(Long id);

    /**
     * 批量删除预定
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFuhangBookingByIds(Long[] ids);
}
