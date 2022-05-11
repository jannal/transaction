package org.jannal.account0.core.user.service;


import org.jannal.account0.core.user.entity.TransferSerial;

public interface AccountTransferService {

    /**
     * 尝试转账
     */
    public void tryPayment(TransferSerial transferSerial);

    /**
     * 确定扣款
     */
    public void confirmPayment(String transferSerialNumber);


    /**
     * 取消扣款
     */
    public void cancelPayment(TransferSerial transferSerial);

}
