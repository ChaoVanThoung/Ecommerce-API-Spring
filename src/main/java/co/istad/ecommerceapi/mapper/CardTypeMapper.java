package co.istad.ecommerceapi.mapper;

import co.istad.ecommerceapi.features.card.dto.CardTypeRequest;
import co.istad.ecommerceapi.features.card.dto.CardTypeResponse;
import co.istad.ecommerceapi.domain.CardType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {

    CardType fromCardTypeRequest(CardTypeRequest cardTypeRequest);

    List<CardTypeResponse> toCardTypeListResponse(List<CardType> cardTypes);

    CardTypeResponse toCardTypeResponse(CardType cardType);
}
