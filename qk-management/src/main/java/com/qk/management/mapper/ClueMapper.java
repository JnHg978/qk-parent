package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.clue.ClueQueryDTO;
import com.qk.entity.Clue;
import com.qk.vo.ClueVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:17
 * @Description:
 */
@Mapper
public interface ClueMapper extends BaseMapper<Clue> {
    Page<ClueVO> selectPage(Page<ClueQueryDTO> page, ClueQueryDTO clueQueryDTO);
}
