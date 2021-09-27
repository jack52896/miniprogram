package com.hdjtlgbbs.program.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-13 15:33:52
 */
@Data
@TableName("comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 回复的id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 发送回复贴的用户id
	 */
	private Integer openId;
	/**
	 * 当前回复的帖子id
	 */
	private Integer entityId;
	/**
	 * 会话的类型[回复的是哪个实体 0-帖子 1-用户实体]
	 */
	private Integer entityType;
	/**
	 * 回复的目标[如果回复的是实体用户，则为实体用户的id，不然为0]
	 */
	private Integer targetId;
	/**
	 * 回复的具体内容
	 */
	private String content;
	/**
	 * 回复的状态[0-已发送 1-被删除 2-违反社区公约被封禁]
	 */
	private Integer status;
	/**
	 * 回复的时间
	 */
	private Date createTime;

}
