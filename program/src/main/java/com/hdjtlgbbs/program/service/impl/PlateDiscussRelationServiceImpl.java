package com.hdjtlgbbs.program.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;

import com.hdjtlgbbs.program.dao.PlateDiscussRelationDao;
import com.hdjtlgbbs.program.entity.PlateDiscussRelationEntity;
import com.hdjtlgbbs.program.service.PlateDiscussRelationService;


@Service("plateDiscussRelationService")
public class PlateDiscussRelationServiceImpl extends ServiceImpl<PlateDiscussRelationDao, PlateDiscussRelationEntity> implements PlateDiscussRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlateDiscussRelationEntity> page = this.page(
                new Query<PlateDiscussRelationEntity>().getPage(params),
                new QueryWrapper<PlateDiscussRelationEntity>()
        );

        return new PageUtils(page);
    }

}