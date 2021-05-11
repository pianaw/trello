package ru.kpfu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.Board;
import ru.kpfu.entity.User;
import ru.kpfu.repositories.BoardRepository;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.trelloapi.dto.BoardDto;
import ru.kpfu.trelloapi.services.BoardService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<BoardDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BoardDto> findById(Long aLong) {
        return Optional.empty();
    }

    public BoardDto save(BoardDto boardDto) {
        User user = userRepository.getOne(boardDto.getParticipant_id());
        Board board = Board.builder()
                .title(boardDto.title)
                .build();

        Set<User> users = new HashSet<>();
        users.add(user);
        board.setUsers(users);
        return modelMapper.map(boardRepository.save(board), BoardDto.class);
    }

    @Override
    public Boolean delete(BoardDto boardDto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }

    @Override
    public List<BoardDto> getUserBoards(Long id) {
        return boardRepository.findByUsers_id(id).stream()
                .map(x -> modelMapper.map(x, BoardDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean addUser(String email, Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findByEmail(email).get();

        board.getUsers().add(user);
        boardRepository.save(board);

        return true;
    }
}
