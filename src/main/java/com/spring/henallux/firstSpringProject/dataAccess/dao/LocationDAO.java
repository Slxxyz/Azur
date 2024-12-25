package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.LocationEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationDAO implements LocationDataAccess {

    private LocationRepository locationRepository;

    @Autowired
    public LocationDAO(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public ArrayList<LocationEntity> getAllLocations() {
        List<LocationEntity> locationEntities = locationRepository.findAll();
        return new ArrayList<>(locationEntities);
    }

    @Override
    public LocationEntity getLocationById(Integer locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    @Override
    public LocationEntity saveLocation(LocationEntity locationEntity) {
        return locationRepository.save(locationEntity);
    }

    @Override
    public void deleteLocation(Integer id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Location avec l'ID " + id + " n'existe pas.");
        }
    }
}
