package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/files")
public class FileController {



    private final FileService filesService;

    public FileController(FileService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        filesService.addFileToResponse(fileName, response);
    }

}
