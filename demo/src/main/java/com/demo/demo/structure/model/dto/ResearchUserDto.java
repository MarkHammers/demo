package com.demo.demo.structure.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResearchUserDto {
    private String name;
    private String lastname;
    private String email;
}
