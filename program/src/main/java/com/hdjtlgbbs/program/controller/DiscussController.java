package com.hdjtlgbbs.program.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdjtlgbbs.program.entity.DiscussImagesRelationEntity;
import com.hdjtlgbbs.program.entity.WxUserEntity;
import com.hdjtlgbbs.program.service.DiscussImagesRelationService;
import com.hdjtlgbbs.program.service.WxUserService;
import com.hdjtlgbbs.program.util.ProgramUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.hdjtlgbbs.program.entity.DiscussEntity;
import com.hdjtlgbbs.program.service.DiscussService;
import com.common.utils.PageUtils;
import com.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:09:24
 */
@Slf4j
@RestController
@RequestMapping("program/discuss")
public class DiscussController {
    @Autowired
    private DiscussService discussService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private DiscussImagesRelationService discussImagesRelationService;
    @Autowired
    private OSS ossClient;
    @Value("${alibaba.cloud.BucketName}")
    private String bucketName;
    @Value("${alibaba.cloud.endpoint}")
    private String endpoint;
    private static final int BASE_SORT = 0;
    @PostMapping("/commit")
    @Transactional(rollbackFor = Exception.class)
    public R commit(@RequestBody Map<String, Object> map){
        System.out.println(map);
        String title = (String) map.get("title");
        String content = discussService.saveDiscuss(map);
        String openId = (String) map.get("openId");
        List<WxUserEntity> list = wxUserService.list(new QueryWrapper<WxUserEntity>()
                .eq("open_id", openId));
        if(list.size()!=0 && list.get(0).getStatus() != 0){
            DiscussEntity discussEntity = new DiscussEntity();
            discussEntity.setOpenId(openId);
            discussEntity.setContent(content);
            discussEntity.setTitle(title);
            discussEntity.setCreateTime(new Date());
            discussEntity.setUpdateTime(new Date());
            discussEntity.setSort(BASE_SORT);
            discussEntity.setUserStatus(list.get(0).getStatus());
            discussService.save(discussEntity);

            List<String> urls = (List<String>) map.get("fileUrl");
            System.out.println(urls);
            if(urls.size() != 0) {
                urls.forEach((item) -> {
                    DiscussImagesRelationEntity entity = new DiscussImagesRelationEntity();
                    entity.setDiscussId(discussEntity.getDiscussId());
                    entity.setImages(item);
                    discussImagesRelationService.save(entity);
                });
            }
        }

        return R.ok();
    }
    @PostMapping("/upload")
    public R upload(MultipartFile multipartFile)  {
        InputStream inputStream = null;
        String url = null;
        try {
            inputStream =  multipartFile.getInputStream();
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String resultName = ProgramUtil.generateUUID()+suffix;
            url = "https://"+bucketName+"."+endpoint+"/"+resultName;
            ossClient.putObject(bucketName, resultName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("data", url);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = discussService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{discussId}")
    public R info(@PathVariable("discussId") Integer discussId){
		DiscussEntity discuss = discussService.getById(discussId);

        return R.ok().put("discuss", discuss);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussEntity discuss){
		discussService.save(discuss);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DiscussEntity discuss){
		discussService.updateById(discuss);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] discussIds){
		discussService.removeByIds(Arrays.asList(discussIds));

        return R.ok();
    }

}
