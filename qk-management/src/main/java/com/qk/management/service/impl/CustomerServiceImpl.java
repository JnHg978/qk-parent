package com.qk.management.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.customer.CustomerQueryDTO;
import com.qk.entity.Customer;
import com.qk.management.mapper.CustomerMapper;
import com.qk.management.service.CustomerService;
import com.qk.vo.customer.CustomerVO;
import org.springframework.stereotype.Service;

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
}
