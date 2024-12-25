package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.LocationEntity;

import java.util.ArrayList;

public interface LocationDataAccess {
    ArrayList<LocationEntity> getAllLocations();
    LocationEntity getLocationById(Integer locationId);
    LocationEntity saveLocation(LocationEntity locationEntity);
    void deleteLocation(Integer id);
}
