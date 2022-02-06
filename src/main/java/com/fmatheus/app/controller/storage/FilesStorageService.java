package com.fmatheus.app.controller.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import com.fmatheus.app.controller.dto.response.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

    String save(MultipartFile file, FileResponse fileResponse);

    Resource load(FileResponse fileResponse);

    Stream<Path> loadAll(FileResponse fileResponse);

    void deleteFile(FileResponse fileResponse);


}
