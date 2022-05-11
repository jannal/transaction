package org.jannal.account1.facade;


import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.jannal.account1.facade.dto.AccountReceiveFacadeRequestDTO;

public interface AccountReceiveFacadeService {
    /**
     *   @TwoPhaseBusinessAction: 定义两阶段提交
     *   name = 该tcc的bean名称,全局唯一
     *   commitMethod = confirmTransfer为二阶段确认方法
     *   rollbackMethod = cancelTransfer为二阶段取消方法
     *   BusinessActionContextParameter注解 传递参数到二阶段中
     * try阶段，from给to转钱
     */
    @TwoPhaseBusinessAction(name = "accountReceiveFacadeService",
            commitMethod = "confirmReceive",
            rollbackMethod = "cancelReceive")
    boolean prepareReceive(@BusinessActionContextParameter(paramName = "accountReceiveFacadeRequestDTO")
                                   AccountReceiveFacadeRequestDTO accountReceiveFacadeRequestDTO);

    /**
     * 确认方法，保证与commitMethod一致
     */
    boolean confirmReceive(BusinessActionContext context);

    /**
     * 确认方法，保证与rollbackMethod一致
     */
    boolean cancelReceive(BusinessActionContext context);

}
