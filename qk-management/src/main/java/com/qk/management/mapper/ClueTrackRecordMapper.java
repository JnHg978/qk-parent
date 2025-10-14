package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.entity.ClueTrackRecord;
import com.qk.vo.clue.ClueFollowVO;
import com.qk.vo.clue.ClueTrackRecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/14 10:40
 * @Description:
 */
@Mapper
public interface ClueTrackRecordMapper extends BaseMapper<ClueTrackRecord> {
    List<ClueTrackRecordVO> selectByClueId(Integer id);
}
