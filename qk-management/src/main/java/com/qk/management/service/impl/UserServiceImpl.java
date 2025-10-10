package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.qk.common.PageResult;
import com.qk.domain.UserDO;
import com.qk.dto.LoginDTO;
import com.qk.dto.UserDTO;
import com.qk.entity.User;
import com.qk.management.mapper.RoleMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.UserService;
import com.qk.util.JwtUtils;
import com.qk.vo.LoginResultVo;
import com.qk.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:30
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageResult<UserVO> page(UserDTO userDTO) {
        Long total = userMapper.count(userDTO);
        Integer offset = (userDTO.getPage() - 1) * userDTO.getPageSize();
        userDTO.setPage(offset);
        List<UserDO> result = userMapper.select(userDTO);
        List<UserVO> rows = BeanUtil.copyToList(result, UserVO.class);
        return PageResult.<UserVO>builder()
                .total(total)
                .rows(rows)
                .build();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()+"123").getBytes()));
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
            throw new RuntimeException("参数错误");
        }
        User user = userMapper.selectByUsername(loginDTO);
        if (user == null){
            throw new RuntimeException("账号/密码错误！");
        } else {
            boolean isEqual = Objects.equals(user.getPassword(), DigestUtils.md5DigestAsHex((loginDTO.getPassword()).getBytes()));
            if (!isEqual){
                throw new RuntimeException("账号/密码错误！");
            }else {
                LoginResultVo loginResultVo = LoginResultVo.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .name(user.getName())
                        .image(user.getImage())
                        .roleLabel(roleMapper.selectById(user.getRoleId()).getLabel())
                        .build();
                Map<String, Object> claims = BeanUtil.beanToMap(loginResultVo,  false, true);
                loginResultVo.setToken(JwtUtils.generateToken(claims));
                return loginResultVo;
            }

        }
    }

}
