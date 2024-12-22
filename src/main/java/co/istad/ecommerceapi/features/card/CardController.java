package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.features.card.dto.CardRequest;
import co.istad.ecommerceapi.features.card.dto.CardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;


    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @DeleteMapping("/delete/{cvv}")
    CardResponse deleteByCvv(@PathVariable("cvv") String cvv) {
        return cardService.deleteByCvv(cvv);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping("/createNew")
    CardResponse createNew(@Valid @RequestBody CardRequest cardRequest) {
        log.info("Received request: {}", cardRequest);
        return cardService.createNew(cardRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/findAll")
    List<CardResponse> findAll() {
        return cardService.findAll();
    }


}
