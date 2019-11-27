package com.census.zipcode.data.controller;

import com.census.zipcode.data.dto.ResponseDTO;
import com.census.zipcode.data.dto.Status;
import com.census.zipcode.data.services.ZipCodeDataFetcherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CensusZipCodeControllerTest {

    @InjectMocks
    private CensusZipCodeController censusZipCodeController;

    @Mock
    private ZipCodeDataFetcherService zipCodeDataFetcherService;

    private Long start;
    private Long end;
    @BeforeEach
    public void setUp() {

        start = 10L;
        end = 20L;
    }

    @Test
      void getZipCodesHavingPopulationInGivenRange() {
        ResponseDTO responseDTO = new ResponseDTO(Status.SUCCESS, Arrays.asList(101L, 102L));
        when(zipCodeDataFetcherService.fetchZipCodesForPopulationWithinGivenRange(start, end)).thenReturn(responseDTO);
        ResponseEntity <ResponseDTO> responseEntity = censusZipCodeController.getZipCodesHavingPopulationInGivenRange(start, end);
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertTrue(responseEntity.hasBody());
        assertTrue(responseEntity.getBody().getResult().size() != 0 );

    }

    @Test
    void getZipCodesHavingMedianInGivenRange() {
        ResponseDTO responseDTO = new ResponseDTO(Status.SUCCESS, Arrays.asList(101L, 102L));
        when(zipCodeDataFetcherService.fetchZipCodesMedianInGivenRange(10.2, 20.3)).thenReturn(responseDTO);
        ResponseEntity <ResponseDTO> responseEntity = censusZipCodeController.getZipCodesHavingMedianInGivenRange(10.2, 20.3);
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertTrue(responseEntity.hasBody());
        assertTrue(responseEntity.getBody().getResult().size() != 0 );
    }

    @Test
    void getTopMostPopulatedZipCodes() {
        ResponseDTO responseDTO = new ResponseDTO(Status.SUCCESS, Arrays.asList(101L, 102L));
        when(zipCodeDataFetcherService.fetchTopMostPopulatedZipCodes(10L)).thenReturn(responseDTO);
        ResponseEntity <ResponseDTO> responseEntity = censusZipCodeController.getTopMostPopulatedZipCodes(10L);
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertTrue(responseEntity.hasBody());
        assertTrue(responseEntity.getBody().getResult().size() != 0 );
    }

    @Test
    void getZipCodesWithMoreFemales() {
        ResponseDTO responseDTO = new ResponseDTO(Status.SUCCESS, Arrays.asList(101L, 102L));
        when(zipCodeDataFetcherService.fetchZipCodesWithMoreFemales()).thenReturn(responseDTO);
        ResponseEntity <ResponseDTO> responseEntity = censusZipCodeController.getZipCodesWithMoreFemales();
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertTrue(responseEntity.hasBody());
        assertTrue(responseEntity.getBody().getResult().size() != 0 );
    }
}