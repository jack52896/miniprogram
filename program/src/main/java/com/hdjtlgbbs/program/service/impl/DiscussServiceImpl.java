package com.hdjtlgbbs.program.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;

import com.hdjtlgbbs.program.dao.DiscussDao;
import com.hdjtlgbbs.program.entity.DiscussEntity;
import com.hdjtlgbbs.program.service.DiscussService;


@Service("discussService")
public class DiscussServiceImpl extends ServiceImpl<DiscussDao, DiscussEntity> implements DiscussService {
    private static final String LOST_TYPE = "失物招领";
    private static final String EXPRESS_TYPE = "表白";
    private static final String HELP_TYPE = "跑腿";
    private static final String DUCSS_TYPE = "闲聊";
    private static final String OLD_TYPE = "二手市场";
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DiscussEntity> page = this.page(
                new Query<DiscussEntity>().getPage(params),
                new QueryWrapper<DiscussEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public String saveDiscuss(Map<String, Object> map) {
        String content = null;
        String title = (String) map.get("title");
        try {
            switch (title){
                case LOST_TYPE:
                    return content
                            = "物品类型:<b>"+ map.get("type")+"</b>\n"+
                              "发现地点:<b>"+ map.get("site")+"</b>\n"+
                              "发现时间:<b>"+ map.get("time")+"</b>\n"+
                              "发现时间:<b>"+ map.get("contact")+"</b>\n"+
                              "发现时间:<b>"+ map.get("text")+"</b>\n";
                case EXPRESS_TYPE:
                    if("1".equals(map.get("type"))){
                        content = "表白:<b>\n"+map.get("text")+"</b>\n";
                    }
                    if("2".equals(map.get("type"))){
                        content = "给自己找对象:<b>\n"+map.get("text")+"</b>\n";
                    }
                    return content;
                case HELP_TYPE:
                    return content
                            = "跑腿事件:<b>"+ map.get("type")+"</b>\n"+
                            "价格:<b>"+ map.get("price")+"</b>\n"+
                            "联系方式:<b>"+ map.get("contact")+"</b>\n"+
                            "详细说明:<b>"+ map.get("text")+"</b>\n";
                case DUCSS_TYPE:
                    return content
                            = "标题:<b>"+ map.get("caption")+"</b>\n"+
                            "内容:<b>"+ map.get("text")+"</b>\n";
                case OLD_TYPE:
                    return content
                            = "物品类型:<b>"+ map.get("use")+"</b>\n"+
                            "新旧程度:<b>"+ map.get("type")+"</b>\n"+
                            "原价:<b>"+ map.get("oldprice")+"</b>\n"+
                            "售价:<b>"+ map.get("nowprice")+"</b>\n"+
                            "联系方式:<b>"+ map.get("contact")+"</b>\n"+
                            "详细介绍:<b>"+ map.get("text")+"</b>\n";
                default: break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}