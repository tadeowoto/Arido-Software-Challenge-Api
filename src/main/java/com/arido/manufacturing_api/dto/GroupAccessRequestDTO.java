package com.arido.manufacturing_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAccessRequestDTO {
    private Long groupId;
    private Long accessLevelId;
}
