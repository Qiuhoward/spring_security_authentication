package com.example.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MkdirRequest {
    private String dirName;
    private Integer userId;
    private String dirPath;
    private Integer pageId;
}
