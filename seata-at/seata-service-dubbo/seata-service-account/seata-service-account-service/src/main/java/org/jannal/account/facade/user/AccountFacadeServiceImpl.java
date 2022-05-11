package org.jannal.account.facade.user;


import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.jannal.account.core.user.entity.User;
import org.jannal.account.core.user.service.UserService;
import org.jannal.account.facade.AccountFacadeService;
import org.jannal.account.facade.dto.UserRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService(version = "1.0.0")
public class AccountFacadeServiceImpl implements AccountFacadeService {
    @Autowired
    private UserService userService;

    @Override
    public void registerUser(UserRequestDTO userRequestDTO) {
        log.info("全局事务id:{}", RootContext.getXID());
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);
        userService.register(user);
    }
}
