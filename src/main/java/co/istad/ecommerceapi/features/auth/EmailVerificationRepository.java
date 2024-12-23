package co.istad.ecommerceapi.features.auth;

import co.istad.ecommerceapi.domain.EmailVerification;
import co.istad.ecommerceapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Integer> {
    Optional<EmailVerification> findByUser (User user);
}
