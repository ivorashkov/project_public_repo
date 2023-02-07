package com.example.web.controller;

import com.example.web.constant.ConstantMessages;
import com.example.web.model.dto.UserDTO;
import com.example.web.service.FileService;
import com.example.web.service.UserService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class FileController {
    private final FileService fileService;
    private final UserService userService;
    private final ValidatorUtil validatorUtil;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> response(@PathVariable Long id) {
        return this.validatorUtil.responseEntity(userService.findUserById(id));
    }

    @PostMapping("/upload/all")
    public String handleFileUploadAll(@RequestParam("file") List<MultipartFile> files,
                                      @RequestParam(name = "userId") Long userId,
                                      @RequestParam(name = "offerId", defaultValue = "-1") Long offerId
    ) {

        files.forEach(file -> handleFileUpload(file, userId, offerId));

        return "All files are saved.";
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(name = "userId") Long userId,
                                   @RequestParam(name = "offerId", defaultValue = "-1") Long offerId
    ) {

        Path initPath = fileService.initialization(userId, offerId, ConstantMessages.FORMAT_ADDON_TEMPLATE);

        fileService.store(file, userId, initPath);

        return "file" + file.getOriginalFilename() + " saved.";
    }

//    private void fileProcessing(Long userId, Long offerId, MultipartFile file, String format) {
//
//        /** http://localhost:8091/home/upload?userId=1&offerId=-1 */
//        Path initPath = fileService.init(userId, offerId, format);
//
//        fileService.store(file, userId, initPath);
//    }

//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }

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
