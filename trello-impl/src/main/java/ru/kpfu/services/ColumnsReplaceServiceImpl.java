package ru.kpfu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.repositories.CardColumnRepository;
import ru.kpfu.trelloapi.dto.ElementsReplaceDto;
import ru.kpfu.trelloapi.services.ColumnsReplaceService;

import java.util.Optional;

@Service
public class ColumnsReplaceServiceImpl implements ColumnsReplaceService {

    @Autowired
    private CardColumnRepository repository;

    @Override
    public void replace(ElementsReplaceDto columns) {
        repository.replace(columns.getElemId1(), columns.getOrderId2());
        repository.replace(columns.getElemId2(), columns.getOrderId1());
    }
}
