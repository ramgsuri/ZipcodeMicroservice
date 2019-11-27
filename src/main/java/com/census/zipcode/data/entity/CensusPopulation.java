package com.census.zipcode.data.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

/**
 * this is the entiry to save all records of data file
 */
@Entity
@Table(name = "census_population")
@AllArgsConstructor
@NoArgsConstructor
public class CensusPopulation {

    @Id
    @GeneratedValue(generator = "app-uuid")
    @GenericGenerator(name = "app-uuid", strategy = "uuid")
    private String id;

    @Column(name="zipcode",unique = true)
    private Long zipCode;

    @Column(name = "total_population")
    private Long totalPopulation;

    @Column(name = "median_age")
    private BigDecimal medianAge;

    @Column(name = "total_males")
    private Long totalMales;

    @Column(name = "total_females")
    private Long totalFemales;

    @Column(name = "total_households")
    private Long totalHouseholds;

    @Column(name = "avg_household_size")
    private BigDecimal averageHouseholdSize;

}
