package com.himasha.request;

import com.himasha.model.Address;
import lombok.Data;

@Data

public class OrderRequest {
    private Long resturantId;
    private Address deliveryAddress;
}
