package com.hdjtlgbbs.program.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdjtlgbbs.program.entity.DiscussImagesRelationEntity;
import com.hdjtlgbbs.program.entity.WxUserEntity;
import com.hdjtlgbbs.program.service.DiscussImagesRelationService;
import com.hdjtlgbbs.program.service.LikeService;
import com.hdjtlgbbs.program.service.WxUserService;
import com.hdjtlgbbs.program.util.ProgramUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private LikeService likeService;
    @Autowired
    private OSS ossClient;
    @Value("${alibaba.cloud.prod.BucketName}")
    private String bucketName;
    @Value("${alibaba.cloud.endpoint}")
    private String endpoint;
    private static final int BASE_SORT = 0;
    private static final String DCODE = "DATECODE";
    private static final String RCODE = "DiscussLikeSortkey";
    @GetMapping("/listDiscussPost/{params}")
    public R listPost(@PathVariable("params") String params){
        //按最新发布对帖子进行排序
        List<DiscussEntity> discussEntities = null;
        if (params.equals(DCODE)) {
            discussEntities = discussService.listByDate();
            for (DiscussEntity discussEntity : discussEntities) {
                Map<String, Object> map = new HashMap<>();
                map.put("discuss_id",discussEntity.getDiscussId());
                List<DiscussImagesRelationEntity> discussImagesRelationEntities = discussImagesRelationService.listByAsc(map);
                List<String> images = new ArrayList<>();
                discussImagesRelationEntities.forEach((item)->{
                    images.add(item.getImages());
                });
                discussEntity.setFormatDate(format(discussEntity.getCreateTime()));
                discussEntity.setImages(images);
            }
        }else if(params.equals(RCODE)){
            //按点赞热度对帖子进行排序
            Set<Object> objs = likeService.getZsetValue(params);
            Set<Integer> ids = null;
            for (Object obj : objs) {
                int id = (int) obj;
                ids.add(id);
            }
            discussEntities = discussService.listByIds(ids);
        }else{
            return R.error().put("msg","传输的code不合法");
        }

        return R.ok().put("post",discussEntities);
    }
    @PostMapping("/commit")
    public R commit(@RequestBody  Map<String, Object> map){
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
            if(urls.size() != 0) {
                urls.forEach((item) -> {
                    DiscussImagesRelationEntity entity = new DiscussImagesRelationEntity();
                    entity.setDiscussId(discussEntity.getDiscussId());
                    entity.setImages(item);
                    entity.setCreateTime(new Date());
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
        } catch (Exception e) {
            return R.ok().put("msg", multipartFile.getOriginalFilename()+"上传失败");
        }
        if(url == null && url.equals("")){
            return R.ok().put("msg", "请检查图片格式是否正确或检查图片大小");
        }
        return R.ok().put("data", url);
    }
    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = discussService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{discussId}")
    public R info(@PathVariable("discussId") Integer discussId){
		DiscussEntity discuss = discussService.getById(discussId);

        return R.ok().put("discuss", discuss);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody DiscussEntity discuss){
		discussService.save(discuss);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody DiscussEntity discuss){
		discussService.updateById(discuss);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Integer[] discussIds){
		discussService.removeByIds(Arrays.asList(discussIds));

        return R.ok();
    }
    private static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
