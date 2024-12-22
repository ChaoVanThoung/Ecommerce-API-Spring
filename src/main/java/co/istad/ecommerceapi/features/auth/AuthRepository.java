package co.istad.ecommerceapi.features.auth;

import co.istad.ecommerceapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {

//    Optional<User> findByEmail(String email);

    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);

}
