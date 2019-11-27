package com.census.zipcode.data.services;

import com.census.zipcode.data.dao.ZipCodeRepository;
import com.census.zipcode.data.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.primitives.Longs.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZipCodeDataFetcherServiceTest {

    @InjectMocks
    private ZipCodeDataFetcherService zipCodeDataFetcherService;

    @Mock
    private ZipCodeRepository zipCodeRepository;

    private Long start;
    private Long end;

    @BeforeEach
    void setUp() {
        start= 1L;
        end = 300L;
    }

    @Test
    void fetchZipCodesForPopulationWithinGivenRange() {

        when(zipCodeRepository.fetchZipCodesForPopulationWithinGivenRange(start, end)).thenReturn(asList(123L, 345L));
        ResponseDTO responseDTO = zipCodeDataFetcherService.fetchZipCodesForPopulationWithinGivenRange(start, end);
        assertTrue(responseDTO.getStatus().name().equals("SUCCESS"));
        assertTrue(responseDTO.getResult().size() !=0 );
        assertTrue(responseDTO.getResult().contains(123L));
        assertTrue(responseDTO.getResult().contains(345L));
    }

    @Test
    void fetchZipCodesMedianInGivenRange() {
        when(zipCodeRepository.fetchZipCodesMedianInGivenRange(1.1, 3.5)).thenReturn(asList(123L, 345L));
        ResponseDTO responseDTO = zipCodeDataFetcherService.fetchZipCodesMedianInGivenRange(1.1, 3.5);
        assertTrue(responseDTO.getStatus().name().equals("SUCCESS"));
        assertTrue(responseDTO.getResult().size() !=0 );
        assertTrue(responseDTO.getResult().contains(123L));
        assertTrue(responseDTO.getResult().contains(345L));
    }

    @Test
    void fetchTopMostPopulatedZipCodes() {
        when(zipCodeRepository.fetchTopMostPopulatedZipCodes(10L)).thenReturn(asList(123L, 345L));
        ResponseDTO responseDTO = zipCodeDataFetcherService.fetchTopMostPopulatedZipCodes(10L);
        assertTrue(responseDTO.getStatus().name().equals("SUCCESS"));
        assertTrue(responseDTO.getResult().size() !=0 );
        assertTrue(responseDTO.getResult().contains(123L));
        assertTrue(responseDTO.getResult().contains(345L));
    }

    @Test
    void fetchZipCodesWithMoreFemales() {
        when(zipCodeRepository.fetchZipCodesWithMoreFemales()).thenReturn(asList(123L, 345L));
        ResponseDTO responseDTO = zipCodeDataFetcherService.fetchZipCodesWithMoreFemales();
        assertTrue(responseDTO.getStatus().name().equals("SUCCESS"));
        assertTrue(responseDTO.getResult().size() !=0 );
        assertTrue(responseDTO.getResult().contains(123L));
        assertTrue(responseDTO.getResult().contains(345L));
    }
}