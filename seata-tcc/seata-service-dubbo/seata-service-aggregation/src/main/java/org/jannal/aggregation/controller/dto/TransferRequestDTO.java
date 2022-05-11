package org.jannal.aggregation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class TransferRequestDTO implements Serializable {
    private static final long serialVersionUID = 6851794280101498332L;

    /**
     * 转账账户
     */
    private String accountFromId;
    /**
     * 收款账户
     */
    private String accountToId;
    /**
     * 转账金额
     */
    private BigDecimal amount;
    /**
     * 转账序列号，标识本次转账唯一性
     */
    private String transferSerialNumber;

}