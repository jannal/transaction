package org.jannal.points.core.user.dao.mapper;

import org.jannal.points.core.user.entity.Points;


public interface PointsMapper {

    public int insert(Points points);

    public int update(Points points);

    public Points findByUserId(String userId);

}
    
