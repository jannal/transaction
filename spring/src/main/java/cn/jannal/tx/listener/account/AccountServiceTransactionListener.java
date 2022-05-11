package cn.jannal.tx.listener.account;

import cn.jannal.tx.listener.account.service.AccountEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class AccountServiceTransactionListener {

    @TransactionalEventListener
    public void handleAccountEvent(AccountEvent event) {
        log.info("{}提交事务", event.getSource());
    }
}
