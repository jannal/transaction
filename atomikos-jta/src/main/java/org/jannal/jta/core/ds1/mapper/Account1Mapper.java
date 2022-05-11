package org.jannal.jta.core.ds1.mapper;

import org.apache.ibatis.annotations.Param;
import org.jannal.jta.core.entity.Account;


public interface Account1Mapper {

    int update(Account account);

    Account findByAccountId(@Param("accountId") String accountId);

    Account findByAmountIdForUpdate(@Param("accountId") String accountId);
}
    
