package com.fmatheus.app.controller.storage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.fmatheus.app.controller.dto.response.FileResponse;
import com.fmatheus.app.controller.rule.ErrorSystemRule;
import com.fmatheus.app.controller.rule.MessageResponseRule;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FilesStorageServiceImpl.class);

    @Autowired
    private MessageResponseRule messageResponseRule;

    @Autowired
    private ErrorSystemRule errorSystemRule;

    @Override
    public String save(MultipartFile file, FileResponse fileResponse) {
        var fileName = fileResponse.getFileName().concat(fileResponse.getExtension());
        var path = this.path(fileResponse.getPath()).resolve(fileName);
        try {
            logger.info("Salvando arquivo {}", fileName);
            this.generateFile(file, path.toFile(), fileResponse);
            return fileName;
        } catch (Exception e) {
            logger.error("Erro {}", e.getMessage());
            this.errorSystemRule.save(e);
            throw this.messageResponseRule.fileStorageException();
        }
    }

    @Override
    public Resource load(FileResponse fileResponse) {
        try {
            var file = this.path(fileResponse.getPath()).resolve(fileResponse.getFileName());
            var resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw this.messageResponseRule.couldNotReadException();
            }
        } catch (MalformedURLException e) {
            this.errorSystemRule.save(e);
            throw this.messageResponseRule.fileStorageException();
        }
    }

    @Override
    public void deleteFile(FileResponse fileResponse) {
        var path = this.path(fileResponse.getPath()).resolve(fileResponse.getFileName());
        FileSystemUtils.deleteRecursively(path.toFile());
    }


    @Override
    public Stream<Path> loadAll(FileResponse fileResponse) {
        var path = this.path(fileResponse.getPath());
        try {
            return Files.walk(path, 1).filter(filter -> !filter.equals(path)).map(path::relativize);
        } catch (IOException e) {
            throw this.messageResponseRule.couldNotReadException();
        }
    }

    private Path path(String directory) {
        return Paths.get(directory);
    }


    private void generateFile(MultipartFile file, File dest, FileResponse fileResponse) {
        try {
            Thumbnails.of(file.getInputStream()).size(fileResponse.getWidth(), fileResponse.getHeight()).outputQuality(Double.parseDouble(fileResponse.getOutputQuality())).outputFormat(fileResponse.getExtension().replaceAll("\\.", "")).toFile(dest);
        } catch (IOException e) {
            this.errorSystemRule.save(e);
            throw this.messageResponseRule.fileStorageException();
        }
    }

}
