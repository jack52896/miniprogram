package com.hdjtlgbbs.program.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;

import com.hdjtlgbbs.program.dao.DiscussImagesRelationDao;
import com.hdjtlgbbs.program.entity.DiscussImagesRelationEntity;
import com.hdjtlgbbs.program.service.DiscussImagesRelationService;


@Service("discussImagesRelationService")
public class DiscussImagesRelationServiceImpl extends ServiceImpl<DiscussImagesRelationDao, DiscussImagesRelationEntity> implements DiscussImagesRelationService {
    @Autowired
    DiscussImagesRelationDao discussImagesRelationDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DiscussImagesRelationEntity> page = this.page(
                new Query<DiscussImagesRelationEntity>().getPage(params),
                new QueryWrapper<DiscussImagesRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DiscussImagesRelationEntity> listByAsc(Map map) {
        return discussImagesRelationDao.selectList(new QueryWrapper<DiscussImagesRelationEntity>()
                .eq("discuss_id", map.get("discuss_id")).orderByDesc("id"));
    }
}