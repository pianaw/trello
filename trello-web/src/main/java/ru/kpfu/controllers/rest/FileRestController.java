package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.services.FileStorageService;
import ru.kpfu.trelloapi.dto.FileDto;
import ru.kpfu.trelloapi.dto.UploadFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/board/{boardId}/col/{columnId}/card/{cardId}/file")
public class FileRestController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<FileDto> upload(@PathVariable Long cardId, @RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileStorageService.store(file, cardId));
    }

    @GetMapping
    public ResponseEntity<List<FileDto>> getFiles(@PathVariable Long cardId) {
        return ResponseEntity.ok(fileStorageService.getAllFiles(cardId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        FileDto file = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.type))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }
}
