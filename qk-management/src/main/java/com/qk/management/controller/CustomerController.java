package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.customer.CustomerQueryDTO;
import com.qk.management.service.CustomerService;
import com.qk.vo.customer.CustomerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hjh
 * @Date: 2025/10/16 11:30
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Result page(CustomerQueryDTO customerQueryDTO) {
        log.info("分页查询客户");
        PageResult<CustomerVO> result = customerService.page(customerQueryDTO);
        return Result.success(result);
    }
}
