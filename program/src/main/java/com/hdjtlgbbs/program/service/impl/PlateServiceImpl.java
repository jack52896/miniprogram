package com.hdjtlgbbs.program.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;

import com.hdjtlgbbs.program.dao.PlateDao;
import com.hdjtlgbbs.program.entity.PlateEntity;
import com.hdjtlgbbs.program.service.PlateService;


@Service("plateService")
public class PlateServiceImpl extends ServiceImpl<PlateDao, PlateEntity> implements PlateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlateEntity> page = this.page(
                new Query<PlateEntity>().getPage(params),
                new QueryWrapper<PlateEntity>()
        );

        return new PageUtils(page);
    }

}