package com.awoyomi_moneymie_assessment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car", schema = "public")
public class Car {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column
    private String vin;
    @Column
    private String brand;
    @Column
    private Integer year;
    @Column
    private String color;



}
