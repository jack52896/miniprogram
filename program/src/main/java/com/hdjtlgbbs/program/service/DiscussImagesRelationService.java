package com.hdjtlgbbs.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.utils.PageUtils;
import com.hdjtlgbbs.program.entity.DiscussImagesRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:29:51
 */
public interface DiscussImagesRelationService extends IService<DiscussImagesRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DiscussImagesRelationEntity> listByAsc(Map map);
}

