package org.jannal.account1.core.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.jannal.account1.core.account.entity.Account;


public interface AccountMapper {

    public Account findById(@Param("id") Long id);

    public int update(Account account);

    Account findByAmountIdForUpdate(@Param("accountId") String accountId);
}
    
