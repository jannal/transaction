package org.jannal.aggregation.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jannal.account.facade.AccountFacadeService;
import org.jannal.account.facade.dto.UserRequestDTO;
import org.jannal.points.facade.PointsFacadeService;
import org.jannal.points.facade.dto.PointsRequestDTO;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterAggregationService {
    @DubboReference(version = "1.0.0")
    private AccountFacadeService accountFacadeService;
    @DubboReference(version = "1.0.0")
    private PointsFacadeService pointsFacadeService;

    /**
     * 注册用户，增加积分
     */
    @GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 200000)
    public void register(UserRequestDTO userRequestDTO, PointsRequestDTO pointsRequestDTO) {
        log.info("全局事务id:{}", RootContext.getXID());
        // 为了模拟注册失败，将增加积分放在前面。测试注册失败，积分是否会增加
        pointsFacadeService.increasePoints(pointsRequestDTO);
        accountFacadeService.registerUser(userRequestDTO);

    }
}
