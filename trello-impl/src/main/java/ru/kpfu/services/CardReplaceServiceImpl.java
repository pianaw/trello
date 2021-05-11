package ru.kpfu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.repositories.CardRepository;
import ru.kpfu.trelloapi.dto.ElementsReplaceDto;
import ru.kpfu.trelloapi.services.CardReplaceService;

@Service
public class CardReplaceServiceImpl implements CardReplaceService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void replace(ElementsReplaceDto elementsReplaceDto) {
        cardRepository.replace(elementsReplaceDto.elemId1, elementsReplaceDto.orderId2);
        cardRepository.replace(elementsReplaceDto.elemId2, elementsReplaceDto.orderId1);
    }
}
