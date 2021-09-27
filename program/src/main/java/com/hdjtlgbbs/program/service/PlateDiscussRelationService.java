package com.hdjtlgbbs.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.utils.PageUtils;
import com.hdjtlgbbs.program.entity.PlateDiscussRelationEntity;

import java.util.Map;

/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:09:25
 */
public interface PlateDiscussRelationService extends IService<PlateDiscussRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

