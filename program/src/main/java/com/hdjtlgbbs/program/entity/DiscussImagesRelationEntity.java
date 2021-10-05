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
 * @date 2021-09-12 15:29:51
 */
@Data
@TableName("discuss_images_relation")
public class DiscussImagesRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	private String images;

	private int discussId;

	private Date createTime;
}
