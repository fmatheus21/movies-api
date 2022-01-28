package com.fmatheus.app.controller.storage;

import com.fmatheus.app.controller.dto.response.FileResponse;
import com.fmatheus.app.controller.enumerable.AppPropertiesEnum;
import com.fmatheus.app.controller.exception.FileStorageException;
import com.fmatheus.app.controller.rule.ErrorSystemRule;
import com.fmatheus.app.controller.rule.MessageResponseRule;
import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.controller.util.LocalDatetUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileServiceStorage {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceStorage.class);

    private Path fileStorageLocation;

    private final FilePropertiesStorage filePropertiesStorage;

    @Autowired
    private ErrorSystemRule errorSystemRule;

    @Autowired
    private MessageResponseRule messageResponseRule;

    @Autowired
    public FileServiceStorage(FilePropertiesStorage filePropertiesStorage) {
        this.filePropertiesStorage = filePropertiesStorage;
        this.fileStorageLocation = Paths.get(filePropertiesStorage.getUploadDir()).toAbsolutePath().normalize();
    }


    private void createDirector(String path) {
        this.fileStorageLocation = Paths.get(this.filePropertiesStorage.getUploadDir().concat(path))
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Não foi possível criar o diretório onde os arquivos carregados serão armazenados.", ex);
        }
    }

    public String returnPath(FileResponse fileResponse) {
        this.fileStorageLocation = Paths.get(this.filePropertiesStorage.getUploadDir().concat(fileResponse.getPath())).toAbsolutePath().normalize();
        return this.fileStorageLocation.toAbsolutePath().toString();
    }


    public File returnFile(AppPropertiesEnum appPropertiesEnum, String fileName) {
        var file = Paths.get(this.filePropertiesStorage.getUploadDir().concat(appPropertiesEnum.getValue()).concat(fileName)).toAbsolutePath().normalize().toAbsolutePath().toString();
        return new File(file);
    }

    private void copyFile(File source, File dest) throws IOException {
        FileCopyUtils.copy(source, dest);
    }

    public String copyFileAvatarDefault() {
        var filenameDefault = "avatar.png";
        var filename = LocalDatetUtil.returnsMillisecondsOfDateTime().toString().concat(".").concat(AppPropertiesEnum.IMAGE_EXTENSION.getValue());
        File source = this.returnFile(AppPropertiesEnum.SYSTEM_PATH, File.separator.concat(filenameDefault));
        File dest = this.returnFile(AppPropertiesEnum.AVATAR_PATH, File.separator.concat(filename));

        try {
            this.copyFile(source, dest);
            logger.info("Arquivo de origem: {}", source.getAbsolutePath());
            logger.info("Arquivo de destino: {}", dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            this.errorSystemRule.save(e);
            return null;
        }

        return filename;

    }


    public String storeFile(MultipartFile file, String path, int width, int height) {

        this.createDirector(path);
        String fileName = String.valueOf(LocalDatetUtil.returnsMillisecondsOfDateTime()).concat(".").concat(AppPropertiesEnum.IMAGE_EXTENSION.getValue());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException(this.messageResponseRule.fileStorageException() + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            this.generate(targetLocation.toFile(), targetLocation.toFile(), width, height);

            return fileName;
        } catch (IOException ex) {
            // TODO: Implementar salvamento de erro no banco
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }
    }

    /**
     * Salva um novo arquivo mantendo o nome ja existente.
     */
    public String storeFileUpdate(MultipartFile file, String path, String fileName, int width, int height) {

        this.createDirector(path);

        fileName = AppUtil.removeExtension(fileName);
        fileName = Objects.requireNonNull(fileName).concat(".").concat(AppPropertiesEnum.IMAGE_EXTENSION.getValue());

        if (Objects.requireNonNull(fileName).contains("..")) {
            throw new FileStorageException("O nome do arquivo contém uma sequência de caminho inválida " + fileName);
        }

        try {

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            this.generate(targetLocation.toFile(), targetLocation.toFile(), width, height);

            return fileName;

        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }

    }

    private void generate(File origin, File dest, int width, int height) throws IOException {
        Thumbnails.of(origin)
                .size(width, height)
                .outputQuality(0.8)
                .outputFormat(AppPropertiesEnum.IMAGE_EXTENSION.getValue())
                .toFile(dest);
    }

}

