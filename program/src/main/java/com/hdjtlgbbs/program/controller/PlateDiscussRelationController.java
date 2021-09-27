package com.hdjtlgbbs.program.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdjtlgbbs.program.entity.PlateDiscussRelationEntity;
import com.hdjtlgbbs.program.service.PlateDiscussRelationService;
import com.common.utils.PageUtils;
import com.common.utils.R;



/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:09:25
 */
@RestController
@RequestMapping("program/platediscussrelation")
public class PlateDiscussRelationController {
    @Autowired
    private PlateDiscussRelationService plateDiscussRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plateDiscussRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		PlateDiscussRelationEntity plateDiscussRelation = plateDiscussRelationService.getById(id);

        return R.ok().put("plateDiscussRelation", plateDiscussRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PlateDiscussRelationEntity plateDiscussRelation){
		plateDiscussRelationService.save(plateDiscussRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PlateDiscussRelationEntity plateDiscussRelation){
		plateDiscussRelationService.updateById(plateDiscussRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		plateDiscussRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
