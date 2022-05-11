package cn.jannal.tx.txmanager.annotation;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个绑定到特定事务管理器的注解
 *
 * @Transactional默认的事务管理器名称为transactionManager
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional("transactionManager0")
public @interface Transactional_0 {
}
