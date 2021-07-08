package com.vodafone.carfactory.service;

import com.vodafone.carfactory.dal.Cabrio;
import com.vodafone.carfactory.dal.Hatchback;
import com.vodafone.carfactory.dal.Sedan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CarImpl implements CarService {

    @Override
    public ResponseEntity<?> getType(String carType) {

        carType = carType.toLowerCase(new Locale("tr-TR"));
        switch (carType) {
            case "cabrio":
                return ResponseEntity.ok(new Cabrio().getType());
            case "hatchback":
                return ResponseEntity.ok(new Hatchback().getType());
            case "sedan":
                return ResponseEntity.ok(new Sedan().getType());
            default:
                return ResponseEntity.badRequest()
                        .body("Car type is invalid. Car types must be cabrio, sedan or hatchback");
        }
    }
}
