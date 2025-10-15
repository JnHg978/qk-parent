package com.qk.management.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.constant.BusinessStatusConstants;
import com.qk.dto.business.BizQueryDTO;
import com.qk.entity.Business;
import com.qk.enums.CommonEnum;
import com.qk.exception.CommonBizException;
import com.qk.management.mapper.BizMapper;
import com.qk.management.service.BizService;
import com.qk.vo.BizVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: hjh
 * @Date: 2025/10/14 20:29
 * @Description:
 */
@Service
public class BizServiceImpl extends ServiceImpl<BizMapper, Business> implements BizService {
    @Override
    public PageResult<BizVO> page(BizQueryDTO bizQueryDTO) {
        Page<BizVO> page = Page.of(bizQueryDTO.getPage(), bizQueryDTO.getPageSize());
        Page<BizVO> result = baseMapper.selectPage(page, bizQueryDTO);
        return PageResult.<BizVO>builder()
                .total(result.getTotal())
                .rows(result.getRecords())
                .build();
    }

    @Override
    public void addBusiness(Business business) {
        if(ObjectUtil.isEmpty(business.getPhone()) || ObjectUtil.isEmpty(business.getName())){
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        business.setStatus(BusinessStatusConstants.WAIT_ALLOCATION);
        baseMapper.insert(business);
    }

    @Override
    public void assign(Integer businessId, Integer userId) {
        Business business = baseMapper.selectById(businessId);
        if(ObjectUtil.isEmpty(business)){
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        business.setUserId(userId);
        business.setStatus(BusinessStatusConstants.WAIT_FOLLOW);
        business.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(business);
    }
}
