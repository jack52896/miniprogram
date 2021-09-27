package com.hdjtlgbbs.program.controller;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.R;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hdjtlgbbs.program.entity.WxUserEntity;
import com.hdjtlgbbs.program.service.WxUserService;
import com.common.utils.PageUtils;
import org.springframework.web.client.RestTemplate;


/**
 * 
 *
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-11 13:43:53
 */
@RestController
@RequestMapping("program/wxuser")
public class WxUserController {
    @Autowired
    private WxUserService wxUserService;
    @Value("${wx.appid}")
    private String APPID ;
    @Value("${wx.secret}")
    private String SECRET;
    @GetMapping("/login")
    public R login(@RequestParam String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
                "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(str);
        Map<String, Object> map = new HashMap<>();
        if(jsonObject != null){
            for(String str2 : jsonObject.keySet()){
                map.put(str2, jsonObject.get(str2));
            }
        }
        Map<String, Object> listMap = new HashMap<>();
        listMap.put("open_id",map.get("openid"));
        List<WxUserEntity> historyUser =wxUserService.listByMap(listMap);
        if(historyUser.size()==0) {
            WxUserEntity wxUserEntity = new WxUserEntity();
            wxUserEntity.setOpenId((String) map.get("openid"));
            wxUserEntity.setSessionKey((String) map.get("session_key"));
            wxUserEntity.setCreateTime(new Date());
            wxUserEntity.setStatus(1);
            wxUserService.save(wxUserEntity);
        }
        return R.ok().put("data", map);

    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		WxUserEntity wxUser = wxUserService.getById(id);

        return R.ok().put("wxUser", wxUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WxUserEntity wxUser){
		wxUserService.save(wxUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WxUserEntity wxUser){
		wxUserService.updateById(wxUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		wxUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
