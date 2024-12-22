package co.istad.ecommerceapi.features.card;

import co.istad.ecommerceapi.features.card.dto.CardTypeRequest;
import co.istad.ecommerceapi.features.card.dto.CardTypeResponse;
import co.istad.ecommerceapi.domain.CardType;
import co.istad.ecommerceapi.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService {

    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;

    @Override
    public CardTypeResponse createNew(CardTypeRequest cardTypeRequest) {

        if (cardTypeRepository.existsByCardTypeName(cardTypeRequest.cardTypeName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Card type already exists");
        }
        CardType cardType = cardTypeMapper.fromCardTypeRequest(cardTypeRequest);
        CardType savedCardType = cardTypeRepository.save(cardType);
        return cardTypeMapper.toCardTypeResponse(savedCardType);
    }

    @Override
    public CardTypeResponse deleteByName(CardTypeRequest cardTypeRequest) {
        CardType cardType = cardTypeRepository.findByCardTypeName(cardTypeRequest.cardTypeName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Card type not found"
                ));
        cardTypeRepository.delete(cardType);

        return cardTypeMapper.toCardTypeResponse(cardType);
    }

    @Override
    public List<CardTypeResponse> finaAll() {

        List<CardType> cardTypeList = cardTypeRepository.findAll();
        if (cardTypeList.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No CardType found"
            );
        }
        return cardTypeMapper.toCardTypeListResponse(cardTypeList);
    }
}
