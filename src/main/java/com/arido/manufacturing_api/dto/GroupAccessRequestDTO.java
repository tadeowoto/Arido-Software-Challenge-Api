package com.arido.manufacturing_api.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccessRequestDTO {
    private Long groupId;
    private Long accessLevelId;
}
