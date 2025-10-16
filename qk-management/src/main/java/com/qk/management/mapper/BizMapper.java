package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.domain.BizInfoDO;
import com.qk.dto.business.BizPoolQueryDTO;
import com.qk.dto.business.BizQueryDTO;
import com.qk.entity.Business;
import com.qk.vo.business.BizVO;
import com.qk.vo.business.BizPoolVO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Author: hjh
 * @Date: 2025/10/14 18:16
 * @Description:
 */
@Mapper
public interface BizMapper extends BaseMapper<Business> {
    Page<BizVO> selectPage(Page<BizVO> page, BizQueryDTO bizQueryDTO);

    Page<BizPoolVO> selectPoolPage(Page<BizPoolVO> page, BizPoolQueryDTO dto);

    BizInfoDO selectBizInfo();
}
