package org.jannal.points.core.user.service.impl;

import org.jannal.points.core.user.dao.mapper.PointsMapper;
import org.jannal.points.core.user.entity.Points;
import org.jannal.points.core.user.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("pointsService")
public class PointsServiceImpl implements PointsService {
    @Autowired
    private PointsMapper pointsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increasePoints(Points points) {
        Points pointsExist = pointsMapper.findByUserId(points.getUserId());
        Date date = new Date();
        if (pointsExist == null) {
            points.setCreateTime(date);
            points.setUpdateTime(date);
            pointsMapper.insert(points);
        } else {
            pointsExist.setNum(pointsExist.getNum() + points.getNum());
            pointsExist.setUpdateTime(date);
            pointsMapper.update(pointsExist);
        }

    }

}


