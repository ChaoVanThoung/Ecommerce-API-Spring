package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.features.card.dto.CardTypeRequest;
import co.istad.ecommerceapi.features.card.dto.CardTypeResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardTypeService {

    CardTypeResponse createNew(@Valid CardTypeRequest cardTypeRequest);

    CardTypeResponse deleteByName(@Valid CardTypeRequest cardTypeRequest);

    List<CardTypeResponse> finaAll();
}
