package com.example.demoCosmos.controller;

import com.example.demoCosmos.model.Audiolist;
import com.example.demoCosmos.model.Todo;
import com.example.demoCosmos.repo.AudioBlobRepo;
import com.example.demoCosmos.repo.TodoRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    TodoRepo todoRepo;

    @Autowired
    AudioBlobRepo audioBlobRepo;

    @GetMapping("/todos")
    public Iterable<Todo> getTodoListData() {
        return todoRepo.getAllTodoList();
    }

    @PostMapping("/insertList")
    public Todo getTodoListData(@RequestBody Todo todo) {
        return todoRepo.save(todo);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAudioFile(@RequestParam("audio") MultipartFile audioFile) throws IOException {
        if (!audioFile.isEmpty()) {
            audioBlobRepo.storeFile(audioFile.getOriginalFilename(),audioFile.getInputStream(), audioFile.getSize());
            return ResponseEntity.ok("Audio uploaded successfully!");
        } else {
            return ResponseEntity.badRequest().body("Audio file is empty");
        }
    }

    @GetMapping("/downloadName/{id}")
    public Audiolist downloadFileName(@PathVariable String id) throws IOException {
        if (!id.isEmpty()) {
            List<String> fileBlobName = audioBlobRepo.listFiles();
            return getFileNamesFromAzureBlob(id,fileBlobName);
        } else {
            return null;
        }
    }

    @GetMapping("/downloadFile/{name}")
    public String downloadFile(@PathVariable String name) throws IOException {
        if (!name.isEmpty()) {
            ByteArrayOutputStream byteArrayOutputStream =  audioBlobRepo.downloadFile(name);

            byte[] byteArray = byteArrayOutputStream.toByteArray();

            return Base64.encodeBase64String(byteArray);
        } else {
            return null;
        }
    }

    private Audiolist getFileNamesFromAzureBlob(String id, List<String> fileBlobName) {

        Audiolist audiolist = new Audiolist();
        List<String> audioFileName = new ArrayList<>();
        for(String filename: fileBlobName){
            if(!filename.isEmpty() && filename.contains(id)){
                audioFileName.add(filename);
            }
        }
        audiolist.setCandidateId(id);
        audiolist.setName(audioFileName);
        return audiolist;
    }
}
