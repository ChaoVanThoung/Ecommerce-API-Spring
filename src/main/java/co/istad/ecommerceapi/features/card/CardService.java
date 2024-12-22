package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.features.card.dto.CardRequest;
import co.istad.ecommerceapi.features.card.dto.CardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    CardResponse deleteByCvv(String cvv);

    CardResponse createNew( CardRequest cardRequest);

    List<CardResponse> findAll();
}
