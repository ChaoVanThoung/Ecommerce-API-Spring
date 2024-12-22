package co.istad.ecommerceapi.features.order;

import co.istad.ecommerceapi.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o JOIN FETCH o.user WHERE o.paymentStatus = true")
    List<Order> findByPaymentStatusTrue();

    Boolean existsByUuid(String uuid);

    Optional<Order> findByUuid(String uuid);
}
