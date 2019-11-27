package com.census.zipcode.data.controller;

import com.census.zipcode.data.dto.UploadFileResponse;
import com.census.zipcode.data.services.DBFileStorageService;
import com.census.zipcode.data.services.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/census")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UploadCensusDataController {

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private DBFileStorageService demographicService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CensusZipCodeController.class);

    /**
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        final String fileName = fileHandler.saveFile(file);
        demographicService.saveCensusData(fileName);

        return new UploadFileResponse(fileName, file.getContentType(), file.getSize());
    }

}
