package com.hdjtlgbbs.program.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdjtlgbbs.program.entity.DiscussImagesRelationEntity;
import com.hdjtlgbbs.program.service.DiscussImagesRelationService;
import com.common.utils.PageUtils;
import com.common.utils.R;



/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:29:51
 */
@RestController
@RequestMapping("program/discussimagesrelation")
public class DiscussImagesRelationController {
    @Autowired
    private DiscussImagesRelationService discussImagesRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = discussImagesRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{discussId}")
    public R info(@PathVariable("discussId") Integer discussId){
		DiscussImagesRelationEntity discussImagesRelation = discussImagesRelationService.getById(discussId);

        return R.ok().put("discussImagesRelation", discussImagesRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussImagesRelationEntity discussImagesRelation){
		discussImagesRelationService.save(discussImagesRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DiscussImagesRelationEntity discussImagesRelation){
		discussImagesRelationService.updateById(discussImagesRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] discussIds){
		discussImagesRelationService.removeByIds(Arrays.asList(discussIds));

        return R.ok();
    }

}
