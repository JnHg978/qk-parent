package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.constant.CommonConstants;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueQueryDTO;
import com.qk.dto.clue.FalseClueDTO;
import com.qk.dto.clue.FollowClueDTO;
import com.qk.entity.Clue;
import com.qk.entity.ClueTrackRecord;
import com.qk.entity.User;
import com.qk.enums.CommonEnum;
import com.qk.exception.CommonBizException;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.mapper.ClueTrackRecordMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.ClueService;
import com.qk.util.CurrentUserHoler;
import com.qk.vo.clue.ClueFollowVO;
import com.qk.vo.clue.ClueTrackRecordVO;
import com.qk.vo.clue.ClueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    @Autowired
    private ClueTrackRecordMapper clueTrackRecordMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void falseClue(Integer id, FalseClueDTO falseClueDTO) {
        Clue clue = baseMapper.selectById(id);
        if (Objects.isNull(clue)) {
            CommonBizException.throwException(CommonEnum.CLUE_NOT_EXIST);
        }
        clue.setStatus(CommonConstants.FALSE_CLUE);
        clue.setUpdateTime(LocalDateTime.now());
        ClueTrackRecord clueTrackRecord = ClueTrackRecord.builder()
                .clueId(id)
                .userId(clue.getUserId())
                .subject(clue.getSubject())
                .level(clue.getLevel())
                .record(falseClueDTO.getRemark())
                .falseReason(falseClueDTO.getReason())
                .type(CommonConstants.FALSE_CLUE)
                .createTime(LocalDateTime.now())
                .build();
        baseMapper.updateById(clue);
        clueTrackRecordMapper.insert(clueTrackRecord);
    }

    @Override
    public ClueFollowVO getById(Integer id) {
        Clue clue = baseMapper.selectById(id);
        if (Objects.isNull(clue)) {
            CommonBizException.throwException(CommonEnum.CLUE_NOT_EXIST);
        }
        ClueFollowVO clueFollowVO = ClueFollowVO.builder()
                .id(clue.getId())
                .phone(clue.getPhone())
                .channel(clue.getChannel())
                .activityId(clue.getActivityId())
                .name(clue.getName())
                .gender(clue.getGender())
                .age(clue.getAge())
                .wechat(clue.getWechat())
                .qq(clue.getQq())
                .userId(clue.getUserId())
                .status(clue.getStatus())
                .subject(clue.getSubject())
                .level(clue.getLevel())
                .nextTime(clue.getNextTime())
                .build();
        List<ClueTrackRecordVO> clueTrackRecordVOS = clueTrackRecordMapper.selectByClueId(id);
        clueFollowVO.setTrackRecords(clueTrackRecordVOS);
        return clueFollowVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void followClue(FollowClueDTO clueDTO) {
        boolean hasNull = BeanUtil.hasNullField(clueDTO, "activityId", "name", "gender", "age", "wechat", "qq", "userId");
        if (hasNull) {
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        Clue clue = Clue.builder()
                .id(clueDTO.getId())
                .activityId(clueDTO.getActivityId())
                .name(clueDTO.getName())
                .gender(clueDTO.getGender())
                .age(clueDTO.getAge())
                .wechat(clueDTO.getWechat())
                .qq(clueDTO.getQq())
                .userId(CurrentUserHoler.getCurrentUser())
                .subject(clueDTO.getSubject())
                .level(clueDTO.getLevel())
                .nextTime(clueDTO.getNextTime())
                .updateTime(LocalDateTime.now())
                .status(CommonConstants.FOLLOWING)
                .build();

        baseMapper.updateById(clue);
        ClueTrackRecord clueTrackRecord = ClueTrackRecord.builder()
                .clueId(clueDTO.getId())
                .userId(CurrentUserHoler.getCurrentUser())
                .subject(clueDTO.getSubject())
                .level(clueDTO.getLevel())
                .record(clueDTO.getRecord())
                .type(CommonConstants.FOLLOWING)
                .createTime(LocalDateTime.now())
                .nextTime(clueDTO.getNextTime())
                .build();
        clueTrackRecordMapper.insertNewRecord(clueTrackRecord);
    }

}
