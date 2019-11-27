package com.census.zipcode.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ZipCodeRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final  String fetchZipcodesForPopulationRange        = "SELECT zipcode FROM census_population WHERE total_population BETWEEN ? AND ? ";
    private static final  String fetchZipcodesForMedianAgeRange         = "SELECT zipcode FROM census_population WHERE median_age BETWEEN ? AND ? ";
    private static final  String fetchZipcodesForTopMostPopulatedRegion = "SELECT zipcode FROM census_population order by total_population desc LIMIT ? " ;
    private static final  String fetchZipcodesForPopulationWhereFemaleAreMoreThanMale
                                                                        = " SELECT zipcode "
                                                                        + " FROM census_population WHERE 1 = "
                                                                        + " CASE WHEN total_females > total_males THEN  1 "
                                                                        + " ELSE 0 "
                                                                        + " END order by total_females -  total_males desc "
                                                                        ;

    public List<Long> fetchZipCodesForPopulationWithinGivenRange(Long start, Long end) {

        List<Long> zipCodes = jdbcTemplate.queryForList(fetchZipcodesForPopulationRange, new Object[] { start, end }, Long.class);
        if(zipCodes == null) {
            return new ArrayList<>();
        }
        return zipCodes;
    }


    public List<Long> fetchZipCodesMedianInGivenRange(Double start, Double end) {

        List<Long> zipCodes = jdbcTemplate.queryForList(fetchZipcodesForMedianAgeRange, new Object[] { start, end }, Long.class);
        if(zipCodes == null) {
            return new ArrayList<>();
        }
        return zipCodes;
    }


    public List<Long> fetchTopMostPopulatedZipCodes(Long top ) {
        List<Long> zipCodes = jdbcTemplate.queryForList(fetchZipcodesForTopMostPopulatedRegion, new Object[] { top }, Long.class);
        if(zipCodes == null) {
            return new ArrayList<>();
        }
        return zipCodes;
    }

    public List<Long>  fetchZipCodesWithMoreFemales() {
        List<Long> zipCodes = jdbcTemplate.queryForList(fetchZipcodesForPopulationWhereFemaleAreMoreThanMale, Long.class);
        if(zipCodes == null) {
            return new ArrayList<>();
        }
        return zipCodes;
    }
}
