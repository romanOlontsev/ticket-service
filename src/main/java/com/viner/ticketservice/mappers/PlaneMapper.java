package com.viner.ticketservice.mappers;

import com.viner.ticketservice.dto.AddOrUpdatePlaneDto;
import com.viner.ticketservice.dto.AddOrUpdateUserDto;
import com.viner.ticketservice.dto.PlaneDto;
import com.viner.ticketservice.entity.Plane;
import com.viner.ticketservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaneMapper {

    PlaneDto planeToPlaneDto(Plane plane);

    Plane planeDtoToPlane(PlaneDto planeDto);

    AddOrUpdatePlaneDto planeToAddOrUpdatePlaneDto(Plane plane);

    Plane addOrUpdatePlaneDtoToPlane(AddOrUpdatePlaneDto planeDto);

    List<PlaneDto> planeListToPlaneDtoList(List<Plane> planes);

    void updatePlaneFromAddOrUpdatePlaneDto(AddOrUpdatePlaneDto addOrUpdatePlaneDto, @MappingTarget Plane plane);
}
