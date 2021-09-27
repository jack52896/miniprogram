package com.hdjtlgbbs.program.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdjtlgbbs.program.entity.PlateEntity;
import com.hdjtlgbbs.program.service.PlateService;
import com.common.utils.PageUtils;
import com.common.utils.R;



/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:09:24
 */
@RestController
@RequestMapping("program/plate")
public class PlateController {
    @Autowired
    private PlateService plateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{plateId}")
    public R info(@PathVariable("plateId") Integer plateId){
		PlateEntity plate = plateService.getById(plateId);

        return R.ok().put("plate", plate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PlateEntity plate){
		plateService.save(plate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PlateEntity plate){
		plateService.updateById(plate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] plateIds){
		plateService.removeByIds(Arrays.asList(plateIds));

        return R.ok();
    }

}
