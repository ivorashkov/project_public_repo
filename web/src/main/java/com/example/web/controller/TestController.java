package com.example.web.controller;

import com.example.web.constant.StorageFileNotFoundException;
import com.example.web.model.dto.RegistrationUserDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.UserRepository;
import com.example.web.service.StorageService;
import com.example.web.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class TestController {
    private final StorageService fileService;
    private final UserService userService;

    @GetMapping("/{id}")
    public  ResponseEntity<UserDTO> response(@PathVariable Long id){
        UserDTO user = userService.findUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        //todo code improvement
        final Long userId = 1l;
        fileService.store(file,userId);
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
