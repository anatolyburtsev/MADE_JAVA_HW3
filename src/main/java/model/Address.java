package model;

import lombok.Builder;

@Builder
public class Address {
    private String zipcode;
    private String street;
    private int buildingNumber;
}
