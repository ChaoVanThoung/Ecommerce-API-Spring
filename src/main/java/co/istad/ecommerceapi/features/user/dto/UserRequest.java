package co.istad.ecommerceapi.features.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank(message = "Name must be Not Blank")
        String name,
        @NotNull (message = "DOB is not null")
        LocalDate dob,
        @NotBlank(message = "Gender must be not blank")
        String gender,
        @NotBlank(message = "Phone Number must be not blank")
        String phoneNumber,
        @NotBlank(message = "Email must be not blank")
        String email,
        @NotBlank(message = "PassWord must be not blank")
        String password,
        @NotBlank(message = "Password must be not blank")
        String confirmPassword,

        String profileImageUrl,

        String cityOrProvince,
        String khanOrDistrict,
        String village,
        String street
) {
}
