package com.demo.demo.structure.model.bin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResearchUserListOutputBin {
    private List<UserSearchOutputBin> userSearchOutputBinList;
}
