package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByCvv(String cvv);

    Boolean existsByCvv(String cvv);

    @Query("SELECT c FROM Card c ")
    Card getCard();
}
