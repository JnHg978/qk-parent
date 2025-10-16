package com.qk.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.entity.Customer;
import com.qk.management.mapper.CustomerMapper;
import com.qk.management.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * @Author: hjh
 * @Date: 2025/10/16 11:28
 * @Description:
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
}
