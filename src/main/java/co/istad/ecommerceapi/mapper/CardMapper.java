package co.istad.ecommerceapi.mapper;

import co.istad.ecommerceapi.features.card.dto.CardRequest;
import co.istad.ecommerceapi.features.card.dto.CardResponse;
import co.istad.ecommerceapi.domain.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {


    Card fromCardRequest(CardRequest cardRequest);@

    Mapping(target = "cardTypeName", expression = "java(card.getCardType() != null ? card.getCardType().getCardTypeName() : null)")
    CardResponse toCardResponse(Card card);

//    Mapping(target = "cardTypeName", expression = "java(card.getCardType() != null ? card.getCardType().getCardTypeName() : null)")
    List<CardResponse> toCardListResponses(List<Card> cards);
}
