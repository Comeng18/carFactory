package com.vodafone.carfactory.service;

import org.springframework.http.ResponseEntity;

public interface CarService {

    ResponseEntity<?> getType(String carType);
}
