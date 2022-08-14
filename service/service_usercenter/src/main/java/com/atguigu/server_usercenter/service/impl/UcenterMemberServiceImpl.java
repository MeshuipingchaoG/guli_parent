package com.atguigu.server_usercenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.server_usercenter.entity.UcenterMember;
import com.atguigu.server_usercenter.entity.vo.RegisterVo;
import com.atguigu.server_usercenter.mapper.UcenterMemberMapper;
import com.atguigu.server_usercenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-22
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String login(UcenterMember ucenterMember) {

        // 获取登录的邮箱和密码
        String mail = ucenterMember.getMail();
        String password = ucenterMember.getPassword();

        //判断登录的邮箱和密码是否为空
        if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)) {
            throw new ChenZhiWeiException(20001, "邮箱或者密码为空，请输入正确的邮箱和密码！");
        }

        // 判断输入的邮箱跟数据库中的邮箱是否一致
        QueryWrapper<UcenterMember> userWrapper = new QueryWrapper<>();
        userWrapper.eq("mail",mail);
        // 在数据库中查出一条user记录
        UcenterMember user = baseMapper.selectOne(userWrapper);

        if (user == null) {
            throw new ChenZhiWeiException(20001, "该用户不存在！");
        }

        // 数据库中的密码是过加密的
        // 我们输入的password是没有加过密的，所以要先进行加密，然后进行比较
        if (!MD5.encrypt(password).equals(user.getPassword())) {
            throw new ChenZhiWeiException(20001, "输入密码有误！");
        }

        if (user.getIsDisabled()) {
            throw new ChenZhiWeiException(20001, "该用户被禁用！");
        }

        // 通过jwt返回token
        String token = JwtUtils.getJwtToken(user.getId(), user.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        // 用户提交的信息
        String nickname = registerVo.getNickname();
        String mail = registerVo.getMail();
        String password = registerVo.getPassword();
        Integer code = registerVo.getCode();

        // 判断提交的信息是否合法
        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(mail)
                || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new ChenZhiWeiException(20001, "注册失败！");
        }

        // 判断验证码
        // 获取redis中的验证码
        Integer redisCode = (Integer) redisTemplate.opsForValue().get(mail);
        if (!code.equals(redisCode)) {
            throw new ChenZhiWeiException(20001, "验证码不正确");
        }

        // 邮箱不能重复，一个邮箱只能注册一个
        QueryWrapper<UcenterMember> userWrapper = new QueryWrapper<>();
        userWrapper.eq("mail", mail);
        Integer count = baseMapper.selectCount(userWrapper);
        if (count > 0) {
            throw new ChenZhiWeiException(20001, "该邮箱已经被注册。");
        }

        // 成功添加
        UcenterMember member = new UcenterMember();
        member.setMail(mail);
        member.setPassword(MD5.encrypt(password));
        member.setNickname(nickname);
        member.setIsDisabled(false);
        member.setAvatar("https://chenzhiwei-edu.oss-cn-beijing.aliyuncs.com/2022/07/16/fdf38ea0ca3b4ebebee8845cd6a3fd20file.png");
        baseMapper.insert(member);
    }

    @Override
    public int selectResisterCount(String day) {

        int resisterCount = baseMapper.getResisterCount(day);
        return resisterCount;
    }
}
