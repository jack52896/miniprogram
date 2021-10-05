package com.hdjtlgbbs.program.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author zyj
 * @email =2901570623@qq.com
 * @date 2021-09-12 15:09:24
 */
@Data
@TableName("discuss")
public class DiscussEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 帖子的id
	 */
	@TableId(type = IdType.AUTO)
	private Integer discussId;
	/**
	 * 发帖人的openid
	 */
	private String openId;
	/**
	 * 发帖的标题
	 */
	private String title;
	/**
	 * 发帖的内容
	 */
	private String content;
	/**
	 * 发帖时间
	 */
	private Date createTime;
	/**
	 * 帖子更新时间
	 */
	private Date updateTime;
	/**
	 * 帖子的权重
	 */
	private Integer sort;
	/**
	 * 发帖人的状态[0-普通用户, 1-管理员]
	 */
	private Integer userStatus;
	@TableField(exist = false)
	private String FormatDate;
	@TableField(exist = false)
	private List<String> images;
}
