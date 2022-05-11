package org.jannal.aggregation.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jannal.account0.facade.AccountTransferFacadeService;
import org.jannal.account0.facade.dto.AccountTransferFacadeRequestDTO;
import org.jannal.account1.facade.AccountReceiveFacadeService;
import org.jannal.account1.facade.dto.AccountReceiveFacadeRequestDTO;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentAggregationService {
    @DubboReference(version = "1.0.0")
    private AccountTransferFacadeService accountTransferFacadeService;
    @DubboReference(version = "1.0.0")
    private AccountReceiveFacadeService accountReceiveFacadeService;

    /**
     * 转账付款
     */
    @GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 200000)
    public void transfer(AccountTransferFacadeRequestDTO accountTransferRequestDTO,
                         AccountReceiveFacadeRequestDTO accountReceiveFacadeRequestDTO) {
        log.info("全局事务id:{},start", RootContext.getXID());
        accountTransferFacadeService.prepareTransfer(accountTransferRequestDTO);
        accountReceiveFacadeService.prepareReceive(accountReceiveFacadeRequestDTO);
        log.info("全局事务id:{},end", RootContext.getXID());
    }
}
