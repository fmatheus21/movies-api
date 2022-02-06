package com.fmatheus.app.controller.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fmatheus.app.controller.constant.ValuePropertiesConstant;
import com.fmatheus.app.controller.dto.response.FileResponse;
import com.fmatheus.app.controller.enumerable.UploadTypeEnum;
import com.fmatheus.app.controller.serializer.Base64Deserializer;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Base64;
import java.util.Objects;

@Component
public class MethodGlobalUtil {

    @Value(ValuePropertiesConstant.UPLOAD_MOVIE_SIZE_WIDTH)
    private String movieSizeWidth;

    @Value(ValuePropertiesConstant.UPLOAD_MOVIE_SIZE_HEIGHT)
    private String movieSizeHeight;

    @Value(ValuePropertiesConstant.UPLOAD_MOVIE_EXTENSION)
    private String movieExtension;

    @Value(ValuePropertiesConstant.UPLOAD_FILE_MOVIE)
    private String path;

    @Value(ValuePropertiesConstant.UPLOAD_MOVIE_QUALITY)
    private String outputQuality;

    public FileResponse uploadFileConfig(UploadTypeEnum type, String fileName) {
        return switch (type) {
            case MOVIE -> FileResponse.builder()
                    .path(path)
                    .fileName(Objects.isNull(fileName) ? LocalDatetUtil.returnsMillisecondsOfDateTime().toString() : fileName)
                    .width(Integer.parseInt(movieSizeWidth))
                    .height(Integer.parseInt(movieSizeHeight))
                    .outputQuality(outputQuality)
                    .extension(movieExtension)
                    .build();
        };
    }

    @JsonDeserialize(using = Base64Deserializer.class)
    @SneakyThrows
    public static String converterImageToBase64(String path) {
        File file = new File(path);
        if (file.exists()) {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            return "data:image/png;base64,".concat(Base64.getEncoder().encodeToString(fileContent));
        }
        return null;

    }


}
