package com.awoyomi_moneymie_assessment;

import com.awoyomi_moneymie_assessment.model.Car;
import com.awoyomi_moneymie_assessment.repository.CarRepository;
import com.awoyomi_moneymie_assessment.response.CarResponseData;
import com.awoyomi_moneymie_assessment.service.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneymieCarApplicationTests {
    @Autowired
    CarRepository repository;
    @Autowired
    CarService service;


    @Test
    public void contextLoads() {
    }


    @Test
    public void getListOfAllCarPopulatedFromJsonObjectShouldBeMoreThanZero() {
        CarResponseData resp = service.getListOfAllCar();
        List<Car> list = resp.getCarList();
        Assert.assertNotEquals(0, list.size());

    }


    @Test
    public void getListOfAllCarByBrandInDescShouldReturnCarsThatDoestNotStartWithA() {
        CarResponseData resp = service.getListOfAllCarByFilter("brand", "desc");
        Car firstCar = resp.getCarList().get(0);
        Assert.assertFalse(firstCar.getBrand().startsWith("A"));

    }


    @Test
    public void getListOfAllCarByBrandInAscShouldReturnCarsThatStartDoesNotStartWithV() {
        CarResponseData resp = service.getListOfAllCarByFilter("brand", "asc");
        Car firstCar = resp.getCarList().get(0);
        Assert.assertFalse(firstCar.getBrand().startsWith("V"));

    }


}
