package org.jannal.points.facade;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.jannal.points.core.user.entity.Points;
import org.jannal.points.core.user.service.PointsService;
import org.jannal.points.facade.dto.PointsRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0")
@Slf4j
public class PointsFacadeServiceImpl implements PointsFacadeService {
    @Autowired
    private PointsService pointsService;

    @Override
    public void increasePoints(PointsRequestDTO pointsRequestDTO) {
        log.info("全局事务id:{}", RootContext.getXID());
        Points points = new Points();
        BeanUtils.copyProperties(pointsRequestDTO, points);
        pointsService.increasePoints(points);
    }
}
