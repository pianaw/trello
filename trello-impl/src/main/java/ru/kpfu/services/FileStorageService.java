package ru.kpfu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.entity.Card;
import ru.kpfu.entity.File;
import ru.kpfu.repositories.FileRepository;
import ru.kpfu.trelloapi.dto.FileDto;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FileDto store(MultipartFile multipartFile, Long cardId) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        File fileDB = File.builder()
                .name(fileName)
                .type(multipartFile.getContentType())
                .card(Card.builder().id(cardId).build())
                .data(multipartFile.getBytes())
                .build();

        File file = fileRepository.save(fileDB);
        return FileDto.builder()
                .id(file.id)
                .name(file.name)
                .cardId(file.getCard().getId())
                .data(file.data)
                .type(file.type)
                .build();
    }

    public List<FileDto> getAllFiles(Long cardId) {
        return fileRepository.findByCard_Id(cardId).stream()
                .map(x -> FileDto.builder()
                        .id(x.id)
                        .name(x.name)
                        .build())
                .collect(Collectors.toList());
    }

    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        return FileDto.builder()
                .type(file.type)
                .name(file.name)
                .data(file.data)
                .build();
    }
}
