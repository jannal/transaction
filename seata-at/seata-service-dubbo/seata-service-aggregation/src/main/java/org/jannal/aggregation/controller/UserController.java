package org.jannal.aggregation.controller;

import org.jannal.account.facade.dto.UserRequestDTO;
import org.jannal.aggregation.common.ApiResponse;
import org.jannal.aggregation.controller.dto.UserRegisterRequestDTO;
import org.jannal.aggregation.service.RegisterAggregationService;
import org.jannal.points.facade.dto.PointsRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private RegisterAggregationService registerAggregationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResponse<?> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUserId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(userRegisterRequestDTO, userRequestDTO);

        PointsRequestDTO pointsRequestDTO = new PointsRequestDTO();
        pointsRequestDTO.setUserId(userRequestDTO.getUserId());
        pointsRequestDTO.setNum(100L);
        registerAggregationService.register(userRequestDTO, pointsRequestDTO);
        return ApiResponse.ok();
    }

}
