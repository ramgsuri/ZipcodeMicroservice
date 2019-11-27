package com.census.zipcode.data.controller;

import com.census.zipcode.data.dto.ResponseDTO;
import com.census.zipcode.data.services.ZipCodeDataFetcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/census/zipcodes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CensusZipCodeController {


	private static final Logger LOGGER = LoggerFactory.getLogger(CensusZipCodeController.class);


	@Autowired
	private ZipCodeDataFetcherService zipcodeDataFetcherService;

	/**
	 * Return all zipcodes which have a total population within range provided by the user.
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("/range")
	public ResponseEntity<ResponseDTO> getZipCodesHavingPopulationInGivenRange(@RequestParam("start") Long start,
			@RequestParam("end") Long end) {
		LOGGER.info("Inside fetch zip codes to fetch zipcoes for population for given range");
		ResponseDTO response = zipcodeDataFetcherService.fetchZipCodesForPopulationWithinGivenRange(start, end);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Return all zipcodes which have a median age within a range provided by the user.
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("/medianage")
	public ResponseEntity<ResponseDTO> getZipCodesHavingMedianInGivenRange(@RequestParam("start") Double start,
			@RequestParam("end") Double end) {
		ResponseDTO response = zipcodeDataFetcherService.fetchZipCodesMedianInGivenRange(start, end);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Return top X number of mostly populated zipcodes.
	 * @param top
	 * @return
	 */
	@GetMapping("/toppolulated")
	public ResponseEntity<ResponseDTO> getTopMostPopulatedZipCodes(@RequestParam("top") Long top) {
		ResponseDTO response = zipcodeDataFetcherService.fetchTopMostPopulatedZipCodes(top);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Return all zipcodes with more females than males ordered by the difference descending.
	 * @return
	 */
	@GetMapping("/genderdiff")
	public ResponseEntity<ResponseDTO> getZipCodesWithMoreFemales() {
		ResponseDTO response= zipcodeDataFetcherService.fetchZipCodesWithMoreFemales();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
