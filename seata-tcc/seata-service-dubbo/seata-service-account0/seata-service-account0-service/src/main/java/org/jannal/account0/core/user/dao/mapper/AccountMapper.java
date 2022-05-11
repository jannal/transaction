package org.jannal.account0.core.user.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.jannal.account0.core.user.entity.Account;


public interface AccountMapper {

    public int update(Account account);

    Account findByAccountId(@Param("accountId") String accountId);

    Account findByAmountIdForUpdate(@Param("accountId") String accountId);
}
    
