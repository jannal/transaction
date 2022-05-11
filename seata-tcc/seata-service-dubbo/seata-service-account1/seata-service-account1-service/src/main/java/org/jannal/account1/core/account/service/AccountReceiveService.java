package org.jannal.account1.core.account.service;

import org.jannal.account1.core.account.entity.TransferSerial;

public interface AccountReceiveService {
    /**
     * 尝试收款
     */
    public void tryReceive(TransferSerial transferSerial);

    /**
     * 确定收款
     */
    public void confirmReceive(String transferSerialNumber);


    /**
     * 取消收款
     */
    public void cancelReceive(TransferSerial transferSerial);


}
