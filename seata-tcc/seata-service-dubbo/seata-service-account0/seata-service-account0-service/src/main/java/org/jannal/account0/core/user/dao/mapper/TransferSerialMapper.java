package org.jannal.account0.core.user.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.jannal.account0.core.user.entity.TransferSerial;


public interface TransferSerialMapper {

    int insert(TransferSerial transferSerial);

    int update(TransferSerial transferSerial);

    TransferSerial findBySerialNumberForUpdate(@Param("transferSerialNumber") String transferSerialNumber);

    TransferSerial findBySerialNumber(@Param("transferSerialNumber") String transferSerialNumber);

}
    
