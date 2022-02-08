package com.awoyomi_moneymie_assessment.repository;

import com.awoyomi_moneymie_assessment.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findAllByOrderByBrandAsc();
    Car getCarByVin(String vin);
    List<Car> findAllBy(String search);

}
