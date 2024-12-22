package co.istad.ecommerceapi.features.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        String uuid,
        String name,
        LocalDate dob,
        String gender,
        String phoneNumber,

        String email,
        String password,
        String profileImageUrl,

        LocalDateTime createdAt,
        Boolean isVerified,
        Boolean isDeleted,

        // Address
        String cityOrProvince,
        String khanOrDistrict,
        String village,
        String street


) {
}
