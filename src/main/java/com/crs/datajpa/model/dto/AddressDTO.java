package com.crs.datajpa.model.dto;

public record AddressDTO (
        Long id, String postalCode, String streetAddress, String complement,
        String neighborhood, String city, String state, String ibgeCode,
        String areaCode
) {

}
