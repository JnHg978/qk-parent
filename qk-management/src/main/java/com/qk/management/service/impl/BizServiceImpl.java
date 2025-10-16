package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.constant.BusinessStatusConstants;
import com.qk.dto.business.BizPoolQueryDTO;
import com.qk.dto.business.BizQueryDTO;
import com.qk.dto.business.FollowBizDTO;
import com.qk.entity.BizTrackRecord;
import com.qk.entity.Business;
import com.qk.enums.CommonEnum;
import com.qk.exception.CommonBizException;
import com.qk.management.mapper.BizMapper;
import com.qk.management.mapper.BizTrackRecordMapper;
import com.qk.management.service.BizService;
import com.qk.util.CurrentUserHoler;
import com.qk.vo.BizVO;
import com.qk.vo.business.BizFollowVO;
import com.qk.vo.business.BizPoolVO;
import com.qk.vo.business.BizTrackRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/14 20:29
 * @Description:
 */
@Service
public class BizServiceImpl extends ServiceImpl<BizMapper, Business> implements BizService {

    @Autowired
    private BizTrackRecordMapper bizTrackRecordMapper;

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

    @Override
    public void back(Integer id) {
        Business business = baseMapper.selectById(id);
        if(ObjectUtil.isEmpty(business)){
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        business.setStatus(BusinessStatusConstants.RECYCLE);
        business.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(business);
    }

    @Override
    public BizFollowVO getRecordById(Integer id) {
        Business business = baseMapper.selectById(id);
        if(ObjectUtil.isEmpty(business)){
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        BizFollowVO bizFollowVO = BeanUtil.copyProperties(business, BizFollowVO.class);
        List<BizTrackRecordVO> trackRecords = bizTrackRecordMapper.selectByBizId(business.getId());
        bizFollowVO.setTrackRecords(trackRecords);
        return bizFollowVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void followBusiness(FollowBizDTO followBizDTO) {
        if(ObjectUtil.isEmpty(followBizDTO.getPhone())
                || ObjectUtil.isEmpty(followBizDTO.getName())
                || ObjectUtil.isEmpty(followBizDTO.getTrackStatus())){
            CommonBizException.throwException(CommonEnum.PARAM_ERROR);
        }
        String keyItems = null;
        if (followBizDTO.getKeyItems().length > 0) {
            keyItems = String.join(",", followBizDTO.getKeyItems());
        }
        BizTrackRecord bizTrackRecord = BizTrackRecord.builder()
                .businessId(followBizDTO.getId())
                .userId(CurrentUserHoler.getCurrentUser())
                .trackStatus(followBizDTO.getTrackStatus())
                .keyItems(keyItems)
                .nextTime(followBizDTO.getNextTime())
                .record(followBizDTO.getRecord())
                .createTime(LocalDateTime.now())
                .build();
        Business business = Business.builder()
                .id(followBizDTO.getId())
                .name(followBizDTO.getName())
                .gender(followBizDTO.getGender())
                .age(followBizDTO.getAge())
                .wechat(followBizDTO.getWechat())
                .qq(followBizDTO.getQq())
                .subject(followBizDTO.getSubject())
                .courseId(followBizDTO.getCourseId())
                .degree(followBizDTO.getDegree())
                .jobStatus(followBizDTO.getJobStatus())
                .channel(followBizDTO.getChannel())
                .remark(followBizDTO.getRemark())
                .status(BusinessStatusConstants.FOLLOWING)
                .nextTime(followBizDTO.getNextTime())
                .updateTime(LocalDateTime.now())
                .build();
        baseMapper.updateById(business);
        bizTrackRecordMapper.insert(bizTrackRecord);
    }

    @Override
    public PageResult<BizPoolVO> poolPage(BizPoolQueryDTO bizPoolQueryDTO) {
        Page<BizPoolVO> page = Page.of(bizPoolQueryDTO.getPage(), bizPoolQueryDTO.getPageSize());
        Page<BizPoolVO> result = baseMapper.selectPoolPage(page, bizPoolQueryDTO);
        return PageResult.<BizPoolVO>builder()
                .total(result.getTotal())
                .rows(result.getRecords())
                .build();
    }
}
