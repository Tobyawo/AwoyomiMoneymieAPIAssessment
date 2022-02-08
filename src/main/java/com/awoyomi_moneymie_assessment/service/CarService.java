package com.awoyomi_moneymie_assessment.service;


import com.awoyomi_moneymie_assessment.model.Car;
import com.awoyomi_moneymie_assessment.repository.CarRepository;
import com.awoyomi_moneymie_assessment.response.CarResponseData;
import com.awoyomi_moneymie_assessment.response.ResponseCodeEnum;
import com.awoyomi_moneymie_assessment.response.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class CarService {

    @Value("${map-db-path:src/main/resources/json/cars-large.json.}")
    String filePath;

    @Autowired
    CarRepository carRepository;

    @Autowired
    EntityManager em;


public ResponseData saveNewCar(Car car){
    ResponseData response = new ResponseData();
    Car carResponse = carRepository.save(car);
    if(carResponse != null){
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray)obj;
            JSONObject jsonObject = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(car));
            jsonArray.add(jsonObject);
            FileWriter file = new FileWriter(filePath);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    response.setCode(ResponseCodeEnum.SUCCESS);
    response.setDescription("Succcessfully saved a new Car");
    return response;
    }else{
        response.setCode(ResponseCodeEnum.ERROR);
        response.setDescription("Error occurred while saving new car to db");
        return response;
    }

}

    public CarResponseData getListOfAllCar() {
        List<Car> list = carRepository.findAllByOrderByBrandAsc();
        if(list != null){
            CarResponseData resp = new CarResponseData(ResponseCodeEnum.SUCCESS, "Successfully retrieved list of all cars");
            resp.setCarList(list);
            return resp;
        }else{
            return new CarResponseData(ResponseCodeEnum.NO_RECORDS_FOUND, ResponseCodeEnum.NO_RECORDS_FOUND.getDescription());}

    }


    public CarResponseData getListOfAllCarByFilter(String params, String direction) {
        List<Car> list = carRepository.findAll(orderByParam(params, direction));
        if(list != null){
            CarResponseData resp = new CarResponseData(ResponseCodeEnum.SUCCESS, "Successfully retrieved list of all cars");
            resp.setCarList(list);
            return resp;
        }else{
        return new CarResponseData(ResponseCodeEnum.NO_RECORDS_FOUND, ResponseCodeEnum.NO_RECORDS_FOUND.getDescription());}
    }

    private Sort orderByParam(String params, String direction) {
        Sort.Direction dir = Sort.Direction.ASC;
        if(direction != null && direction.equalsIgnoreCase("desc")){
            dir = Sort.Direction.DESC;
        }
        return new Sort(dir, params);
    }

    public Car findCarByVin(String vin) {
    Car resp = carRepository.getCarByVin(vin);
        return resp != null? resp:null;
    }


    public CarResponseData findAllCarByParamAndValue(String param, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);

        Root<Car> car = cq.from(Car.class);
        Predicate paramPredicate = cb.or((cb.equal(car.get(param), capitalize(value))),(cb.equal(car.get(param), value.toUpperCase(Locale.ROOT))));
        cq.where(paramPredicate);

        TypedQuery<Car> query = em.createQuery(cq);
        List<Car> list = query.getResultList();
        if(!list.isEmpty()){
        CarResponseData resp = new CarResponseData(ResponseCodeEnum.SUCCESS, "Successfully retrieved list of all cars with " + param +" equals " + value);
        resp.setCarList(list);
        return resp;}
        else{
            return new CarResponseData(ResponseCodeEnum.NO_RECORDS_FOUND, ResponseCodeEnum.NO_RECORDS_FOUND.getDescription());
        }
    }



    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}
