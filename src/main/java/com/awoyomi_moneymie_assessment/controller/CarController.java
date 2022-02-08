package com.awoyomi_moneymie_assessment.controller;


import com.awoyomi_moneymie_assessment.model.Car;
import com.awoyomi_moneymie_assessment.response.CarResponseData;
import com.awoyomi_moneymie_assessment.response.ResponseData;
import com.awoyomi_moneymie_assessment.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    CarService service;


    @PostMapping("/add")
    public ResponseEntity<Object> saveNewCar(@RequestBody Car car){
       ResponseData response =  service.saveNewCar(car);
       return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> listAllCars() {
        CarResponseData resp = service.getListOfAllCar();
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/filter")
    public ResponseEntity<Object> getFilteredListOfCars(@RequestParam("param") String param, @RequestParam(value = "sort", required = false) String sort) {
        CarResponseData resp = service.getListOfAllCarByFilter(param, sort);
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/find")
    public ResponseEntity<Object> findCarByVin(@RequestParam("vin") String vin) {
        Car resp = service.findCarByVin(vin);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getListOfCarsByValue2(@RequestParam("param") String param, @RequestParam("value") String value ) {
        CarResponseData resp = service.findAllCarByParamAndValue(param, value);
        return ResponseEntity.ok(resp);
    }


}
