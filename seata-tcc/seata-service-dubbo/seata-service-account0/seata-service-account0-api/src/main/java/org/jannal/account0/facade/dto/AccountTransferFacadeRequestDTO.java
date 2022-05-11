package org.jannal.account0.facade.dto;

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
public class AccountTransferFacadeRequestDTO implements Serializable {
    private static final long serialVersionUID = 6851794280101498332L;
    /**
     * 转账序列号，标识本次转账唯一性
     */
    private String transferSerialNumber;
    /**
     * 转账用户
     */
    private String accountFromId;
    /**
     * 到账用户
     */
    private String accountToId;
    /**
     * 转账金额
     */
    private BigDecimal amount;


}