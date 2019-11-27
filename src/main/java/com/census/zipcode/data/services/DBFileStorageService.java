package com.census.zipcode.data.services;

import com.census.zipcode.data.dao.CensusPopulationDBRepository;
import com.census.zipcode.data.dto.ErrorDTO;
import com.census.zipcode.data.dto.UploadFileProcessingResponse;
import com.census.zipcode.data.entity.CensusPopulation;
import com.census.zipcode.data.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This service is responsible to load and save file in Database
 */
@Service
public class DBFileStorageService {

  @Autowired
  private CensusPopulationDBRepository censusPopulationDBRepository;

  public void saveCensusData(final String fileName) throws FileUploadException {
    List<String> processingErrorLines = new ArrayList<>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      boolean isFirstLine = true;
      while (line != null) {
        if(isFirstLine) {
          line = reader.readLine();
          isFirstLine = false;
          continue;
        } else {
          persistRecord(line, processingErrorLines);
          line = reader.readLine();
        }
      }
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }


    if(processingErrorLines.size() != 0) {
      throw new FileUploadException("Erro occurred in uploading file");
    }

  }

  private UploadFileProcessingResponse handleProcessingErrors(final List<String> processingErrorLines,
                                      final List<String> duplicateProcessingErrorLines) {
    UploadFileProcessingResponse uploadFileProcessingResponse = new UploadFileProcessingResponse();
    uploadFileProcessingResponse.setHttpStatus(HttpStatus.OK);
    uploadFileProcessingResponse.setMessage("File processed with few errors");

    ErrorDTO[] processingErrors = new ErrorDTO[2];

    ErrorDTO processingError = new ErrorDTO();
    processingError.setReason("Error while parsing line");
    processingError.setErrorLines(processingErrorLines);
    processingErrors[0] = processingError;

    uploadFileProcessingResponse.setErrorDTO(processingErrors);

    return uploadFileProcessingResponse;
  }

  /**
   * this method saves each line in database
   * @param line
   * @param errorLines
   */
  @Transactional
  public void persistRecord(final String line, final List<String> errorLines) throws Exception {

    String[] lineContent = line.split(",");
    if(lineContent.length != 7) {
      errorLines.add(line);
      return;
    }
    try {
      CensusPopulation population = getCensusData(lineContent);
      censusPopulationDBRepository.save(population);
    }  catch (Exception ave) {
        throw new Exception("Error while saving data records in database.");
    }
  }

   CensusPopulation getCensusData(final String[] censusDetails) throws Exception{
    CensusPopulation censusPopulation = null;
    try {
      censusPopulation = new CensusPopulation(
          UUID.randomUUID().toString(),
          Long.valueOf(Long.parseLong(censusDetails[0])),
          Long.valueOf(Long.parseLong(censusDetails[1])),
          new BigDecimal(censusDetails[2]),
          Long.valueOf(Long.parseLong(censusDetails[3])),
          Long.valueOf(Long.parseLong(censusDetails[4])),
          Long.valueOf(Long.parseLong(censusDetails[5])),
          new BigDecimal(censusDetails[6])
      );
    } catch (Exception e) {
      throw new Exception("Error while parsing line");
    }
    return censusPopulation;
  }
}
