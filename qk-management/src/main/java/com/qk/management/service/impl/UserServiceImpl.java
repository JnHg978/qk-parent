package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import com.qk.common.PageResult;
import com.qk.domain.UserDO;
import com.qk.dto.LoginDTO;
import com.qk.dto.UserDTO;
import com.qk.entity.User;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.UserService;
import com.qk.vo.LoginResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:30
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<UserDO> page(UserDTO userDTO) {
        boolean hasNull = BeanUtil.hasNullField(userDTO, "name", "status", "phone", "deptId");
        if (hasNull) {
            throw new RuntimeException("参数异常");
        }
        Long total = userMapper.count(userDTO);
        Integer offset = (userDTO.getPage() - 1) * userDTO.getPageSize();
        userDTO.setPage(offset);
        List<UserDO> userList = userMapper.select(userDTO);
        userList.forEach(userDO -> {
            userDO.setPhone(DesensitizedUtil.mobilePhone(userDO.getPhone()));
        });
        return PageResult.<UserDO>builder()
                .total(total)
                .rows(userList)
                .build();
    }

    @Override
    public void addUser(User user) {
        boolean hasNull = BeanUtil.hasNullField(user, "id", "password", "deptId", "roleId", "image", "remark", "createTime", "updateTime");
        if (hasNull) {
            throw new RuntimeException("参数异常");
        }
        user.setPassword(DigestUtils.md5DigestAsHex((user.getUsername()+"123").getBytes()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public void deleteById(List<Integer> ids) {
        userMapper.deleteById(ids);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updateById(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public List<User> getByRole(String roleLabel) {
        return userMapper.selectByRole(roleLabel);
    }

    @Override
    public List<User> getByDept(Integer deptId) {
        return userMapper.selectByDept(deptId);
    }

    @Override
    public LoginResultVo login(LoginDTO loginDTO) {
        boolean hasNull = BeanUtil.hasNullField(loginDTO);
        if (hasNull) {
            throw new RuntimeException("参数异常");
        }
        loginDTO.setPassword(DigestUtils.md5DigestAsHex((loginDTO.getPassword()).getBytes()));
        LoginResultVo loginResultVo = userMapper.selectByUsernameAndPassword(loginDTO);
        if (loginResultVo != null) {
            loginResultVo.setToken("this is a token");
        }
        return loginResultVo;
    }

}
