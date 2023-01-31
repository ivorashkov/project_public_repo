package com.example.web.controller;

import com.example.web.constant.ConstantMessages;
import com.example.web.constant.StorageFileNotFoundException;
import com.example.web.model.dto.UserDTO;
import com.example.web.service.FileService;
import com.example.web.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class TestController {
    private final FileService fileService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> response(@PathVariable Long id) {
        UserDTO user = userService.findUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(name = "userId") Long userId,
                                   @RequestParam(name = "offerId", defaultValue = "-1") Long offerId
    ) {
        /**
         *  да проверя дали можем да го направим гъвкаво с добавяне на userID,
         *  offerId, и по този начин да се определи дали ще се създава нова директория
         *  къде ще се създава и тн.
         *  http://localhost:8091/home/upload?userId=1&offerId=-1
         */

        Path initPath = fileService.init(userId, offerId, ConstantMessages.FORMAT_ADDON_TEMPLATE);

        fileService.store(file, userId, initPath);

        return "file " + file.getOriginalFilename() + " saved.";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }

}
