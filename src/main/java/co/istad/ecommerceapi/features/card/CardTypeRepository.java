package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {

    @Query("SELECT ct FROM CardType ct WHERE ct.cardTypeName = :name")
    Optional<CardType> findByName(@Param("name") String name);

    Optional<CardType> findByCardTypeName(String cardTypeName);

    Boolean existsByCardTypeName(String cardTypeName);
}
