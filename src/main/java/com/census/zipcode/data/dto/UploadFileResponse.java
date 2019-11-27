package com.census.zipcode.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UploadFileResponse {
  private String fileName;
  private String fileType;
  private long   size;

}
