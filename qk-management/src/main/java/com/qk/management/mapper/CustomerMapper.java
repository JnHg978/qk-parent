package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.customer.CustomerQueryDTO;
import com.qk.entity.Customer;
import com.qk.vo.customer.CustomerVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: hjh
 * @Date: 2025/10/16 10:43
 * @Description:
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
    Page<CustomerVO> selectPage(Page<CustomerVO> page, CustomerQueryDTO dto);
}
