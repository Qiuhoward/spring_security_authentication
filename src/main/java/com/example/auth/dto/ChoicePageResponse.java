package com.example.auth.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ChoicePageResponse{
    private List<Object>list;
}
