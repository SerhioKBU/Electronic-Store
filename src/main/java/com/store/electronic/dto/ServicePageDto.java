package com.store.electronic.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServicePageDto extends BaseDto{
    String productTitle;
    String productType;
    Double price;
}
