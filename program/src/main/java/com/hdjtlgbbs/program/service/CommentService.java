package com.hdjtlgbbs.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.utils.PageUtils;
import com.hdjtlgbbs.program.entity.CommentEntity;

import java.util.Map;

/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-13 15:33:52
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

