package co.istad.ecommerceapi.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileUtil {

    public static String generateFileName(String originalFilename) {
        String newName = ZonedDateTime.now(ZoneId.of("Asia/Phnom_Penh"))
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String extension = extractExtension(originalFilename);

        return String.format("%s.%s", newName, extension);
    }

    public static String extractExtension(String originalFilename) {
        int lastDotIndex = originalFilename.lastIndexOf(".");
        return originalFilename.substring(lastDotIndex + 1);
    }
}



