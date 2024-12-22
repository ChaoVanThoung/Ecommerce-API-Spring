package co.istad.ecommerceapi.features.auth;

import co.istad.ecommerceapi.features.auth.dto.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {


    JwtResponse refreshToken(@Valid RefreshTokenRequest refreshTokenRequest);

    JwtResponse login(@Valid LoginRequest loginRequest);

    void verify(@Valid VerifyRequest verifyRequest);

    void registerUser(@Valid RegisterRequest registerRequest) throws MessagingException;
}
