package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.photo.Attachment;
import com.javohir.lastproject.entity.photo.AttachmentContent;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.repository.AttachmentContentRepository;
import com.javohir.lastproject.repository.AttachmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public ApiResponse upload(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        if (file != null) {
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return new ApiResponse("File uploaded", true, savedAttachment);
        }
        return new ApiResponse("Error with uploading", false);
    }

    @SneakyThrows
    public void download(Integer id, HttpServletResponse response){
        Optional<Attachment> byId = attachmentRepository.findById(id);
            if (byId.isPresent()) {
            Attachment attachment = byId.get();
            response.setHeader("Content", "attachment");
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream("uploadedFiles" + "/" + attachment.getName());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }
}
