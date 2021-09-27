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
@TableName("message")
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 私信会话的id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 发送人的open_id
	 */
	private Integer fromId;
	/**
	 * 接受人的open_Id
	 */
	private Integer toId;
	/**
	 * 私信会话的代码
	 */
	private String conversation;
	/**
	 * 私信会话的状态[0-正常 1-被封禁]
	 */
	private Integer status;
	/**
	 * 创建会话的时间
	 */
	private Date createTime;

}
