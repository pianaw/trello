package ru.kpfu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.Card;
import ru.kpfu.entity.CheckList;
import ru.kpfu.repositories.CheckListRepository;
import ru.kpfu.trelloapi.dto.CheckListDto;
import ru.kpfu.trelloapi.services.CheckListService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckListServiceImpl implements CheckListService {

    @Autowired
    private CheckListRepository checkListRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CheckListDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CheckListDto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public CheckListDto save(CheckListDto checkListDto) {
        CheckList checkList = checkListRepository.save(CheckList.builder()
                .description(checkListDto.description)
                .isDone(true)
                .card(Card.builder()
                        .id(checkListDto.cardId)
                        .build())
                .build()
        );

        return modelMapper.map(checkList, CheckListDto.class);
    }

    @Override
    public Boolean delete(CheckListDto checkListDto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }

    @Override
    public List<CheckListDto> getCheckListsByCardId(Long cardId) {
        return checkListRepository.getCheckListByCard_Id(cardId).stream()
                .map(x -> modelMapper.map(x, CheckListDto.class))
                .collect(Collectors.toList());
    }
}
