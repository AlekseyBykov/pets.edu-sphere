package dev.abykov.pets.edusphere.content.controller;

import dev.abykov.pets.edusphere.content.service.ContentStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

@RestController
@RequestMapping("/contents")
public class ContentController {

    private final ContentStorageService storageService;

    public ContentController(ContentStorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Uploads a file to the content storage (MinIO).
     * @param file multipart file to upload
     * @return S3 key used to access the file
     */
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String key = storageService.uploadFile(file);
        return ResponseEntity.ok(key);
    }

    /**
     * Downloads a file from content storage using its key.
     * @param key the S3 object key
     * @return binary file content
     */
    @GetMapping("/download/{key}")
    public ResponseEntity<byte[]> download(@PathVariable("key") String key) {
        try {
            byte[] fileBytes = storageService.downloadFile(key);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileBytes);
        } catch (NoSuchKeyException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
