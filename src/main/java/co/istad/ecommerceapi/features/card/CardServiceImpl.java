package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.features.card.dto.CardRequest;
import co.istad.ecommerceapi.features.card.dto.CardResponse;
import co.istad.ecommerceapi.domain.Card;
import co.istad.ecommerceapi.domain.CardType;
import co.istad.ecommerceapi.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardTypeRepository cardTypeRepository;
    private final CardMapper cardMapper;

    @Override
    public CardResponse deleteByCvv(String cvv) {

        Card card = cardRepository.findByCvv(cvv)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Cvv not found"));
        cardRepository.delete(card);
        return cardMapper.toCardResponse(card);
    }

    @Override
    public CardResponse createNew(CardRequest cardRequest) {


        if (cardRepository.existsByCvv(cardRequest.cvv())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "CVV already exists");
        }

        if (cardRequest.expiryDate() == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Expiry date must not be null"
            );
        }

        if (LocalDate.now().isAfter(cardRequest.expiryDate())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Card is Expiry Date"
            );
        }

        CardType cardType = cardTypeRepository.findByCardTypeName(cardRequest.cardTypeName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Card type not found"
                ));

        Card card = cardMapper.fromCardRequest(cardRequest);
        card.setCardType(cardType);
        Card savedCard = cardRepository.save(card);

        return cardMapper.toCardResponse(savedCard);
    }


    @Override
    public List<CardResponse> findAll() {

        List<Card> cards = cardRepository.findAll();
        if (cards.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Card not found"
            );
        }

        return cardMapper.toCardListResponses(cards);
    }
}
