package com.hdjtlgbbs.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.utils.PageUtils;
import com.hdjtlgbbs.program.entity.WxUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-11 13:43:53
 */
public interface WxUserService extends IService<WxUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

