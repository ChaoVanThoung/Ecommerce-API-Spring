package co.istad.ecommerceapi.features.file;

import co.istad.ecommerceapi.features.file.dto.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {
    FileResponse upload(MultipartFile file) throws IOException;
}
