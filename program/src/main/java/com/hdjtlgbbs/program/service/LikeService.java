package com.hdjtlgbbs.program.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface LikeService {
    Set<Object> getZsetValue(String key);
    void setRedisLike(int entityType, int entityId, int userId, int entityUserId);
}
