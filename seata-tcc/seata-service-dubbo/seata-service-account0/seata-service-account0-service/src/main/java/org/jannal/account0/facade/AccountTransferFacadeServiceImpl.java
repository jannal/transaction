package org.jannal.account0.facade;

import com.alibaba.fastjson.JSONObject;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.jannal.account0.core.user.entity.TransferSerial;
import org.jannal.account0.core.user.service.AccountTransferService;
import org.jannal.account0.facade.dto.AccountTransferFacadeRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService(version = "1.0.0")
public class AccountTransferFacadeServiceImpl implements AccountTransferFacadeService {

    @Autowired
    private AccountTransferService accountTransferService;

    @Override
    public boolean prepareTransfer(AccountTransferFacadeRequestDTO accountTransferFacadeRequestDTO) {
        TransferSerial transferSerial = new TransferSerial();
        BeanUtils.copyProperties(accountTransferFacadeRequestDTO, transferSerial);
        accountTransferService.tryPayment(transferSerial);
        return true;
    }

    @Override
    public boolean confirmTransfer(BusinessActionContext context) {
        //返回的是com.alibaba.fastjson.JSONObject，不可直接强制转换为对象
        JSONObject jsonContext = (JSONObject) context.getActionContext("accountTransferFacadeRequestDTO");
        AccountTransferFacadeRequestDTO accountTransferRequestDTO = JSONObject.toJavaObject(jsonContext, AccountTransferFacadeRequestDTO.class);
        accountTransferService.confirmPayment(accountTransferRequestDTO.getTransferSerialNumber());
        return true;
    }

    @Override
    public boolean cancelTransfer(BusinessActionContext context) {
        JSONObject jsonContext = (JSONObject) context.getActionContext("accountTransferFacadeRequestDTO");
        AccountTransferFacadeRequestDTO accountTransferRequestDTO = JSONObject.toJavaObject(jsonContext, AccountTransferFacadeRequestDTO.class);
        TransferSerial transferSerial = new TransferSerial();
        BeanUtils.copyProperties(accountTransferRequestDTO, transferSerial);
        accountTransferService.cancelPayment(transferSerial);
        return true;
    }
}
