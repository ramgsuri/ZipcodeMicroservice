package com.census.zipcode.data.dao;

import com.census.zipcode.data.entity.CensusPopulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CensusPopulationDBRepository extends JpaRepository<CensusPopulation, String> {


}
