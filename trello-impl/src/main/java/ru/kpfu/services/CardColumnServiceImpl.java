package ru.kpfu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.Board;
import ru.kpfu.entity.CardColumn;
import ru.kpfu.repositories.CardColumnRepository;
import ru.kpfu.trelloapi.dto.CardColumnDto;
import ru.kpfu.trelloapi.dto.CardDto;
import ru.kpfu.trelloapi.services.CardColumnService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardColumnServiceImpl implements CardColumnService {

    @Autowired
    private CardColumnRepository cardColumnRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public  List<CardColumnDto> getColumnsByBoardId(Long id) {
        List<CardColumn> columns = cardColumnRepository.getAllCardColumnByBoard_IdOrderByOrderId(id);

        return columns.stream()
                .distinct()
                .map(x -> CardColumnDto.builder()
                        .id(x.id)
                        .orderId(x.getOrderId())
                        .cards(x.getCards().stream()
                                .map(y -> CardDto.builder()
                                    .id(y.id)
                                    .title(y.title)
                                    .columnId(y.column.id)
                                    .description(y.description)
                                    .deadline(y.deadline.toString())
                                    .status(y.status.toString())
                                    .orderId(y.getOrderId())
                                    .build())
                                .collect(Collectors.toCollection(LinkedHashSet::new)))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Page<CardColumnDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CardColumnDto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public CardColumnDto save(CardColumnDto cardColumnDto) {
        CardColumn column = cardColumnRepository.save(CardColumn.builder()
                        .isDeleted(false)
                        .board(Board.builder()
                                .id(cardColumnDto.boardId)
                                .build(
                                ))
                        .build());
        column.setOrderId(column.id);
        return modelMapper.map(cardColumnRepository.save(column), CardColumnDto.class);
    }

    @Override
    public Boolean delete(CardColumnDto cardColumnDto) {
        cardColumnRepository.delete(cardColumnDto.id);
        return true;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
