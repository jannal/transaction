package org.jannal.account0.core.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 账户标识
     */
    private String accountId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 冻结金额
     */
    private BigDecimal freezedAmount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}