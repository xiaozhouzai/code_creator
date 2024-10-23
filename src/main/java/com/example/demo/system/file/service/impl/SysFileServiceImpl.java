package com.example.demo.system.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.demo.system.file.dao.SysFileMapper;
import com.example.demo.system.file.domin.pojo.SysFile;
import com.example.demo.system.file.service.SysFileService;
import com.example.demo.system.file.util.MinioUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile>
    implements SysFileService {

    @Resource
    private MinioUtil minioUtil;

    @Override
    public SysFile uploadFile(MultipartFile file) {
        SysFile sysFile = new SysFile();
        sysFile.setName(file.getOriginalFilename());
        sysFile.setSize(file.getSize());
        sysFile.setPath(file.getOriginalFilename());
        String url = minioUtil.uploadFile(file);
        sysFile.setUrl(url);
        save(sysFile);
        return sysFile;
    }
}




