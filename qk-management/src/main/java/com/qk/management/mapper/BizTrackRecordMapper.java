package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.entity.BizTrackRecord;
import com.qk.vo.business.BizTrackRecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/15 21:00
 * @Description:
 */
@Mapper
public interface BizTrackRecordMapper extends BaseMapper<BizTrackRecord> {
    List<BizTrackRecordVO> selectByBizId(Integer id);
}
