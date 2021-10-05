package com.hdjtlgbbs.program.util;

public class RedisUtil {
    public static final String PREFIX = ":";
    public static final String PREFIX_ENTITY_TYPE = "like:entity";
    public static final String PREFIX_USER_LIKE = "like:user";
    /**
     * entityType 0: 为原来的帖子
     *            1：为帖子回复的内容
     * entityId 对应的实体id
     * @param entityType
     * @param entityId
     * @return
     */
    public static String setRedisLike(int entityType, int entityId){
        return PREFIX_ENTITY_TYPE+PREFIX+entityType+PREFIX+entityId;
    }

    /**
     * 如果给帖子点赞则触发
     * @param userId
     * @return
     */
    public static String setUserLike(int userId){
        return PREFIX_USER_LIKE+PREFIX+userId;
    }
}
