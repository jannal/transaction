package org.jannal.jta.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransfer {
    /**
     * 转账账户
     */
    private String accountFromId;
    /**
     * 进账账户
     */
    private String accountToId;
    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 模拟异常
     */
    private boolean mockException;

}
