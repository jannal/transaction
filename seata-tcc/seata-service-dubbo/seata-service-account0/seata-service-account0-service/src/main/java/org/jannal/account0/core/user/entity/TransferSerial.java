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
public class TransferSerial implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    @Getter
    public enum Status {
        PENDING(1, "预扣款"),
        FINISHED(2, "处理完成"),
        DISCARD(3, "废弃");

        private int status;
        private String desc;

        Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }
    }

    /**
     * 主键
     */
    private Long id;
    /**
     * 转账流水号
     */
    private String transferSerialNumber;
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
     * 状态，1待处理，2处理，3废弃
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}