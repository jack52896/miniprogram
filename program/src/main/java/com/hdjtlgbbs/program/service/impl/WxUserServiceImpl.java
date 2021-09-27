package com.hdjtlgbbs.program.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;

import com.hdjtlgbbs.program.dao.WxUserDao;
import com.hdjtlgbbs.program.entity.WxUserEntity;
import com.hdjtlgbbs.program.service.WxUserService;


@Service("wxUserService")
public class WxUserServiceImpl extends ServiceImpl<WxUserDao, WxUserEntity> implements WxUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WxUserEntity> page = this.page(
                new Query<WxUserEntity>().getPage(params),
                new QueryWrapper<WxUserEntity>()
        );

        return new PageUtils(page);
    }

}