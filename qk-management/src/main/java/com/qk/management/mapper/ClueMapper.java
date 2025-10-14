package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.dto.clue.ClueQueryDTO;
import com.qk.entity.Clue;
import com.qk.vo.ClueVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:17
 * @Description:
 */
@Mapper
public interface ClueMapper extends BaseMapper<Clue> {
    List<ClueVO> selectPage(ClueQueryDTO clueQueryDTO);
}
