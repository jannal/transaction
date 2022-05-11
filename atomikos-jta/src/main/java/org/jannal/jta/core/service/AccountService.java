package org.jannal.jta.core.service;

import org.jannal.jta.core.entity.AccountTransfer;

public interface AccountService {

    public void transfer(AccountTransfer accountTransfer);
}
