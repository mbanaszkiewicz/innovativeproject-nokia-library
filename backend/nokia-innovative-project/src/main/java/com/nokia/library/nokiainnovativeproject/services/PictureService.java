package com.nokia.library.nokiainnovativeproject.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nokia.library.nokiainnovativeproject.exceptions.MaxFileSizeException;
import com.nokia.library.nokiainnovativeproject.exceptions.TypeNotSupportedException;
import com.nokia.library.nokiainnovativeproject.exceptions.ValidationException;
import com.nokia.library.nokiainnovativeproject.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.nokia.library.nokiainnovativeproject.utils.Constants.MessageTypes.*;
import static com.nokia.library.nokiainnovativeproject.utils.Constants.Messages;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final List<String> supportedTypes = Arrays.asList (MediaType.IMAGE_JPEG.toString(), MediaType.IMAGE_PNG.toString());
    public Map uploadPicture(MultipartFile picture) throws IOException {
        validateFile(picture);
        Map uploadResult = null;
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "drkqvtiuz",
                "api_key", "878127959754956",
                "api_secret", "tOauaQUIBLKO8Ar7FVOcx0A69d8"));
        uploadResult = cloudinary.uploader().upload(picture.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));
        return uploadResult;
    }

    private void validateFile(MultipartFile file) {
        if(file == null || file.isEmpty()){
            throw new ValidationException("File" + Messages.get(IS_EMPTY));
        }
        if(file.getSize() > Constants.MAX_FILE_SIZE ) { throw new MaxFileSizeException(file.getSize()); }
        if (!supportedTypes.contains(file.getContentType()))
            throw new TypeNotSupportedException(supportedTypes);
    }
}