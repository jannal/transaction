package org.jannal.aggregation.controller;

import org.jannal.account0.facade.dto.AccountTransferFacadeRequestDTO;
import org.jannal.account1.facade.dto.AccountReceiveFacadeRequestDTO;
import org.jannal.aggregation.common.ApiResponse;
import org.jannal.aggregation.controller.dto.TransferRequestDTO;
import org.jannal.aggregation.service.PaymentAggregationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountTransferController {

    @Autowired
    private PaymentAggregationService paymentAggregationService;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public ApiResponse<?> transfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        AccountTransferFacadeRequestDTO accountTransferFacadeRequestDTO = new AccountTransferFacadeRequestDTO();
        BeanUtils.copyProperties(transferRequestDTO, accountTransferFacadeRequestDTO);
        AccountReceiveFacadeRequestDTO accountReceiveFacadeRequestDTO = new AccountReceiveFacadeRequestDTO();
        BeanUtils.copyProperties(transferRequestDTO, accountReceiveFacadeRequestDTO);
        //转账
        paymentAggregationService.transfer(accountTransferFacadeRequestDTO, accountReceiveFacadeRequestDTO);
        return ApiResponse.ok();
    }

}
