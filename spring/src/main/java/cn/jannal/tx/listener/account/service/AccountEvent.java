package cn.jannal.tx.listener.account.service;

import org.springframework.context.ApplicationEvent;

public class AccountEvent extends ApplicationEvent {
    public AccountEvent(Object source) {
        super(source);
    }
}
