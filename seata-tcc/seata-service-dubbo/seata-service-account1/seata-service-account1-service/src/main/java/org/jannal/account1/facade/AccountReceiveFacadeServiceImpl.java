package org.jannal.account1.facade;

import com.alibaba.fastjson.JSONObject;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.jannal.account1.core.account.entity.TransferSerial;
import org.jannal.account1.core.account.service.AccountReceiveService;
import org.jannal.account1.facade.dto.AccountReceiveFacadeRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0")
public class AccountReceiveFacadeServiceImpl implements AccountReceiveFacadeService {
    @Autowired
    private AccountReceiveService accountReceiveService;

    @Override
    public boolean prepareReceive(AccountReceiveFacadeRequestDTO accountReceiveRequestDTO) {
        TransferSerial transferSerial = new TransferSerial();
        BeanUtils.copyProperties(accountReceiveRequestDTO, transferSerial);
        accountReceiveService.tryReceive(transferSerial);
        return true;
    }

    @Override
    public boolean confirmReceive(BusinessActionContext context) {
        //返回的是com.alibaba.fastjson.JSONObject，不可直接强制转换为对象
        JSONObject jsonContext = (JSONObject) context.getActionContext("accountReceiveFacadeRequestDTO");
        AccountReceiveFacadeRequestDTO accountReceiveRequestDTO = JSONObject.toJavaObject(jsonContext, AccountReceiveFacadeRequestDTO.class);
        accountReceiveService.confirmReceive(accountReceiveRequestDTO.getTransferSerialNumber());
        return true;
    }

    @Override
    public boolean cancelReceive(BusinessActionContext context) {
        JSONObject jsonContext = (JSONObject) context.getActionContext("accountReceiveFacadeRequestDTO");
        AccountReceiveFacadeRequestDTO accountReceiveRequestDTO = JSONObject.toJavaObject(jsonContext, AccountReceiveFacadeRequestDTO.class);
        TransferSerial transferSerial = new TransferSerial();
        BeanUtils.copyProperties(accountReceiveRequestDTO, transferSerial);
        accountReceiveService.cancelReceive(transferSerial);
        return true;
    }
}
