package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.business.BizQueryDTO;
import com.qk.dto.business.FollowBizDTO;
import com.qk.entity.Business;
import com.qk.vo.BizVO;
import com.qk.vo.business.BizFollowVO;

/**
 * @Author: hjh
 * @Date: 2025/10/14 20:28
 * @Description:
 */
public interface BizService extends IService<Business> {
    PageResult<BizVO> page(BizQueryDTO bizQueryDTO);

    void addBusiness(Business business);

    void assign(Integer businessId, Integer userId);

    void back(Integer id);

    BizFollowVO getRecordById(Integer id);

    void followBusiness(FollowBizDTO followBizDTO);
}
