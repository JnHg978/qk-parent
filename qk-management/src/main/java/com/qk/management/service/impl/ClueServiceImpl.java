package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.constant.CommonConstants;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueQueryDTO;
import com.qk.entity.Clue;
import com.qk.entity.User;
import com.qk.enums.CommonEnum;
import com.qk.exception.CommonBizException;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.ClueService;
import com.qk.vo.ClueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:19
 * @Description:
 */
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<ClueVO> page(ClueQueryDTO clueQueryDTO) {
        Page<ClueVO> clueVOPage = this.baseMapper.selectPage(Page.of(clueQueryDTO.getPage(), clueQueryDTO.getPageSize()), clueQueryDTO);
        return PageResult.<ClueVO>builder()
                .total(clueVOPage.getTotal())
                .rows(clueVOPage.getRecords())
                .build();
    }

    @Override
    public void insert(ClueDTO clueDTO) {
        if (clueDTO.getPhone() == null || clueDTO.getName() == null) {
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        Clue clue = BeanUtil.copyProperties(clueDTO, Clue.class);
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        clue.setStatus(CommonConstants.WAIT_ALLOCATION);
        baseMapper.insert(clue);
    }

    @Override
    public void assign(Integer clueId, Integer userId) {
        Clue clue = baseMapper.selectById(clueId);
        if (Objects.isNull(clue)) {
            CommonBizException.throwException(CommonEnum.CLUE_NOT_EXIST);
        }
        User user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            CommonBizException.throwException(CommonEnum.USER_NOT_EXIST);
        }
        clue.setUserId(userId);
        clue.setStatus(CommonConstants.FOLLOWING);
        clue.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(clue);
    }
}
