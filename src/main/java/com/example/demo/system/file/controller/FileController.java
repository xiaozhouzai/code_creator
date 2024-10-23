package com.example.demo.system.file.controller;

import com.example.demo.common.Result;
import com.example.demo.system.file.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private SysFileService sysFileService;

    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) {
        return Result.success(sysFileService.uploadFile(file).getUrl());
    }
}
