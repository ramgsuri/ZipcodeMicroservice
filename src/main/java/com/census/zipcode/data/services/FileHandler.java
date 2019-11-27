package com.census.zipcode.data.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileHandler {

  @Value("${file.upload-dir}")
  private String fileUploadDir;

  /**
   * it is the method for storing data file in filesystem after validation
   * @param file
   * @return
   * @throws Exception
   */
  public String saveFile(MultipartFile file) throws Exception{
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      if(fileName.contains("..") || !fileName.contains(".csv")) {
        throw new Exception("Not a valid file");
      }

      Path targetLocation = Paths.get(fileUploadDir + fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileUploadDir + fileName;
    } catch (IOException ex) {
      throw new Exception("Could not store " + fileName + " The path is not correct");
    }
  }
}
