package co.istad.ecommerceapi.features.user;

import co.istad.ecommerceapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(String uuid);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);


}
