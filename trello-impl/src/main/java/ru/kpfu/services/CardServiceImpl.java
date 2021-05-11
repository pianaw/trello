package ru.kpfu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.Card;
import ru.kpfu.entity.CardColumn;
import ru.kpfu.entity.User;
import ru.kpfu.repositories.CardRepository;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.trelloapi.dto.CardColumnDto;
import ru.kpfu.trelloapi.dto.CardDto;
import ru.kpfu.trelloapi.dto.PublicUserDto;
import ru.kpfu.trelloapi.services.CardService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CardDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CardDto> findById(Long aLong) {
        return Optional.empty();
    }


    @Override
    public CardDto save(CardDto cardDto) {
        User user = userRepository.getOne(cardDto.creatorId);

        Card card = Card.builder()
                .title(cardDto.title)
                .column(CardColumn.builder().id(cardDto.columnId).build())
                .description(cardDto.description)
                .status(Card.Status.PROCESS)
                .build();

        Set<User> users = new HashSet<>();
        users.add(user);
        card.setUsers(users);

        card = cardRepository.save(card);
        card.setOrderId(card.id);
        return modelMapper.map(cardRepository.save(card), CardDto.class);
    }

    @Override
    public Boolean delete(CardDto cardDto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }

    @Override
    public PublicUserDto addUser(String email, Long cardId) {
        Card card = cardRepository.findById(cardId).get();
        User user = userRepository.findByEmail(email).get();

        card.getUsers().add(user);
        cardRepository.save(card);

        return modelMapper.map(user, PublicUserDto.class);
    }

    @Override
    public Boolean updateStatus(CardDto card) {
        cardRepository.updateStatus(card.id,  Card.Status.valueOf(card.status));

        return true;
    }

    @Override
    public List<PublicUserDto> getCardParticipants(CardDto cardDto) {
        return userRepository.findCardParticipantsByCard_Id(cardDto.id).stream()
                .map(x -> modelMapper.map(x, PublicUserDto.class))
                .collect(Collectors.toList());
    }
}
