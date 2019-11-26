package model;

import lombok.Builder;

@Builder
public class Address {
    String zipcode;
    String street;
    int buildingNumber;
}
