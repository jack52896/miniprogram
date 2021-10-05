package com.hdjtlgbbs.program.service.impl;

import com.hdjtlgbbs.program.service.LikeService;
import com.hdjtlgbbs.program.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("likeServiceImpl")
public class LikeServiceImpl implements LikeService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Override
    public Set<Object> getZsetValue(String key){
        return redisTemplate.opsForZSet().reverseRange(key, 0, -1);
    }
    @Override
    public void setRedisLike(int entityType, int entityId, int userId, int entityUserId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                //当前实体被点赞数量的key
                String entityLikeKey = RedisUtil.setRedisLike(entityType, entityId);
                //当前实体的主人一共收获了多少赞的key
                String userLikeKey = RedisUtil.setUserLike(entityUserId);
                boolean flag = redisOperations.opsForSet().isMember(entityLikeKey, userId);
                redisOperations.multi();
                if(flag){
                    redisOperations.opsForSet().remove(entityLikeKey, userId);
                    redisOperations.opsForValue().decrement(userLikeKey);
                    if(entityType == 0){
                        int count = (int) redisOperations.opsForValue().get("userLikeKey");
                        redisOperations.opsForZSet().add("DiscussLikeSortkey", count, entityId);
                    }else if(entityType == 1){
                        int count = (int) redisOperations.opsForValue().get("userLikeKey");
                        redisOperations.opsForZSet().add("CommentLikeSortKey", count, entityId);
                    }else{
                        return null;
                    }
                }else{
                    redisOperations.opsForSet().add(entityLikeKey, userId);
                    redisOperations.opsForValue().increment(userLikeKey);
                    if(entityType == 0){
                        int count = (int) redisOperations.opsForValue().get("userLikeKey");
                        redisOperations.opsForZSet().add("DiscussLikeSortkey", count, entityId);
                    }else if(entityType == 1){
                        int count = (int) redisOperations.opsForValue().get("userLikeKey");
                        redisOperations.opsForZSet().add("CommentLikeSortKey", count, entityId);
                    }else{
                        return null;
                    }
                }
                return redisOperations.exec();
            }
        });
    }
}
