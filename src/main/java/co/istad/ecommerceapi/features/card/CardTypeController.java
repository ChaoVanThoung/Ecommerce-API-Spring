package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.features.card.dto.CardTypeRequest;
import co.istad.ecommerceapi.features.card.dto.CardTypeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cardType")
@RequiredArgsConstructor
public class CardTypeController {

    private final CardTypeService cardTypeService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/createNew")
    CardTypeResponse createNew(@Valid @RequestBody CardTypeRequest cardTypeRequest) {
        return cardTypeService.createNew(cardTypeRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    CardTypeResponse deleteByName(@Valid @RequestBody CardTypeRequest cardTypeRequest) {
        return cardTypeService.deleteByName(cardTypeRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    List<CardTypeResponse> findAll() {
        return cardTypeService.finaAll();
    }
}
