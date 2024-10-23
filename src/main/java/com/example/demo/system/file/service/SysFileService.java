package com.example.demo.system.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.system.file.domin.pojo.SysFile;
import org.springframework.web.multipart.MultipartFile;


public interface SysFileService extends IService<SysFile> {

    SysFile uploadFile(MultipartFile file);
}
