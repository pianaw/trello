package ru.kpfu.trelloapi.services;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.trelloapi.dto.ElementsReplaceDto;

public interface ColumnsReplaceService {

    void replace(ElementsReplaceDto columns);
}
