package org.jannal.account1.core.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.jannal.account1.core.account.entity.TransferSerial;


public interface TransferSerialMapper {

    int insert(TransferSerial transferSerial);

    int update(TransferSerial transferSerial);

    TransferSerial findBySerialNumberForUpdate(@Param("transferSerialNumber") String transferSerialNumber);

    TransferSerial findBySerialNumber(@Param("transferSerialNumber") String transferSerialNumber);


}
    
