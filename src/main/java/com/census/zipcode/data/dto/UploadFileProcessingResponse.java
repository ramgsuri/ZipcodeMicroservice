package com.census.zipcode.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadFileProcessingResponse {
  private HttpStatus httpStatus;
  private String     message;
  private ErrorDTO[] errorDTO;
}
