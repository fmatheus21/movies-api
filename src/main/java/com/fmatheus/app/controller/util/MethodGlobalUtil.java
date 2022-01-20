package com.fmatheus.app.controller.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fmatheus.app.controller.dto.response.FileResponse;
import com.fmatheus.app.controller.enumerable.AppPropertiesEnum;
import com.fmatheus.app.controller.enumerable.UploadTypeEnum;
import com.fmatheus.app.controller.serializer.Base64Deserializer;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Base64;

public class MethodGlobalUtil {

    public static FileResponse uploadFileConfig(UploadTypeEnum type) {
        return switch (type) {
            case AVATAR -> FileResponse.builder()
                    .path(AppPropertiesEnum.AVATAR_PATH.getValue())
                    .width(Integer.parseInt(AppPropertiesEnum.AVATAR_SIZE_WIDTH.getValue()))
                    .height(Integer.parseInt(AppPropertiesEnum.AVATAR_SIZE_HEIGHT.getValue()))
                    .build();
            case MOVIE -> FileResponse.builder()
                    .path(AppPropertiesEnum.MOVIE_PATH.getValue())
                    .width(Integer.parseInt(AppPropertiesEnum.MOVIE_SIZE_WIDTH.getValue()))
                    .height(Integer.parseInt(AppPropertiesEnum.MOVIE_SIZE_HEIGHT.getValue()))
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
