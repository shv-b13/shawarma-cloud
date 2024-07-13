package com.example.shawarma.dto;

import lombok.Data;
import java.util.List;

@Data
public
class ShawarmaOrderDto {
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCvv;
    private List<Long> shawarmaIds;
}