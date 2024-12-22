package co.istad.ecommerceapi.features.user.dto;

import java.time.LocalDate;

public record UpdateUserRequest(
        String name,
        LocalDate dob,
        String gender,
        String phoneNumber,
        String email,
        String cityOrProvince,
        String khanOrDistrict,
        String village,
        String street
) {
}
