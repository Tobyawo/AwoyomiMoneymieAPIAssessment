package com.awoyomi_moneymie_assessment.response;

import com.awoyomi_moneymie_assessment.model.Car;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CarResponseData extends ResponseData {
    List<Car> carList;

    public CarResponseData(ResponseCodeEnum responseCodeEnum , String description) {
        setCode(responseCodeEnum);
        setDescription(description);
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
