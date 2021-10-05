package com.hdjtlgbbs.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.utils.PageUtils;
import com.hdjtlgbbs.program.entity.DiscussEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:09:24
 */
public interface DiscussService extends IService<DiscussEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String saveDiscuss(Map<String, Object> map);

    List<DiscussEntity> listByDate();

    List<DiscussEntity> listByLike();
}

