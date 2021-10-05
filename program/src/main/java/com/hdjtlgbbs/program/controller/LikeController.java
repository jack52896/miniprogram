package com.hdjtlgbbs.program.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.utils.R;
import com.hdjtlgbbs.program.entity.WxUserEntity;
import com.hdjtlgbbs.program.service.LikeService;
import com.hdjtlgbbs.program.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/program")
public class LikeController {
    @Autowired
    LikeService likeService;
    @Autowired
    WxUserService wxUserService;

    /**
     * map[key = openid] entityType实体类型 entityId实体对应的id entityUserId发布实体的用户id
     * @return R
     */
    @PostMapping("/like/{entityType}/{entityId}/{entityUserId}")
    public R post(
              @RequestBody Map<String, Object> map
            , @PathVariable("entityType") int entityType
            , @PathVariable("entityId") int entityId
            , @PathVariable("entityUserId") int entityUserId){
        String openId = (String) map.get("openId");
        List<WxUserEntity> wxUserEntityList = wxUserService.list(new QueryWrapper<WxUserEntity>().eq("open_id", openId));
        WxUserEntity wxUserEntity = wxUserEntityList.get(0);
        likeService.setRedisLike(entityType, entityId, wxUserEntity.getId(), entityUserId);
        return R.ok();
    }

}
