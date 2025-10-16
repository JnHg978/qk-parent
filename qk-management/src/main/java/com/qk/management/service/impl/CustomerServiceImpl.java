package com.qk.management.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.customer.CustomerQueryDTO;
import com.qk.entity.Customer;
import com.qk.enums.CommonEnum;
import com.qk.exception.CommonBizException;
import com.qk.management.mapper.CustomerMapper;
import com.qk.management.service.CustomerService;
import com.qk.vo.customer.CustomerVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: hjh
 * @Date: 2025/10/16 11:28
 * @Description:
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Override
    public PageResult<CustomerVO> page(CustomerQueryDTO customerQueryDTO) {
        Page<CustomerVO> page = Page.of(customerQueryDTO.getPage(), customerQueryDTO.getPageSize());
        Page<CustomerVO> customerVOPage = baseMapper.selectPage(page, customerQueryDTO);
        return PageResult.<CustomerVO>builder()
                .total(customerVOPage.getTotal())
                .rows(customerVOPage.getRecords())
                .build();
    }

    @Override
    public void addCustomer(Customer customer) {
        if (ObjectUtil.isEmpty(customer.getPhone()) || ObjectUtil.isEmpty(customer.getName())){
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        baseMapper.insert(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (ObjectUtil.isEmpty(customer.getPhone()) || ObjectUtil.isEmpty(customer.getName())){
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        customer.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(customer);
    }
}
