package org.jannal.points.core.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Points implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户唯一标识
     */
    private String userId;
    /**
     * 积分
     */
    private Long num;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}