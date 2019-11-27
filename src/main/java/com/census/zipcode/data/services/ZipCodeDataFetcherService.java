package com.census.zipcode.data.services;

import com.census.zipcode.data.dao.ZipCodeRepository;
import com.census.zipcode.data.dto.ResponseDTO;
import com.census.zipcode.data.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ZipCodeDataFetcherService {


    @Autowired
    private ZipCodeRepository zipCodeRepository;

    public ResponseDTO fetchZipCodesForPopulationWithinGivenRange(Long start, Long end) {

        List<Long> zipcodeList = zipCodeRepository.fetchZipCodesForPopulationWithinGivenRange(start, end);
        return new ResponseDTO(Status.SUCCESS, zipcodeList);
    }

    public ResponseDTO fetchZipCodesMedianInGivenRange(Double start, Double end) {
        List<Long> zipCodes = zipCodeRepository.fetchZipCodesMedianInGivenRange(start, end);
        return new ResponseDTO(Status.SUCCESS, zipCodes);
    }

    public ResponseDTO fetchTopMostPopulatedZipCodes(Long top) {
        List<Long> zipCodes = zipCodeRepository.fetchTopMostPopulatedZipCodes(top);
        return  new ResponseDTO(Status.SUCCESS, zipCodes);
    }

    public ResponseDTO fetchZipCodesWithMoreFemales() {
        List<Long> zipCodes = zipCodeRepository.fetchZipCodesWithMoreFemales();
        return  new ResponseDTO(Status.SUCCESS, zipCodes);
    }
}
