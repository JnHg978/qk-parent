package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.customer.CustomerQueryDTO;
import com.qk.entity.Customer;
import com.qk.vo.customer.CustomerVO;

/**
 * @Author: hjh
 * @Date: 2025/10/16 11:27
 * @Description:
 */
public interface CustomerService extends IService<Customer> {
    PageResult<CustomerVO> page(CustomerQueryDTO customerQueryDTO);

    void addCustomer(Customer customer);
}
