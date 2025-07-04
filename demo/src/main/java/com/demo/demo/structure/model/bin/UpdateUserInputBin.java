package com.demo.demo.structure.model.bin;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserInputBin {
    private Long id;
    private String name;
    private String lastname;
    private String email;
}
