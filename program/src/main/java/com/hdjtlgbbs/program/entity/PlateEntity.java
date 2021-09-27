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
 * @date 2021-09-12 15:09:24
 */
@Data
@TableName("plate")
public class PlateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 板块id
	 */
	@TableId(type = IdType.AUTO)
	private Integer plateId;
	/**
	 * 板块主题内容
	 */
	private String name;

}
