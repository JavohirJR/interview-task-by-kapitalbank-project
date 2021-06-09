package com.javohir.lastproject.controller;

import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/photo")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping(value = "/upload")
    public ApiResponse upload(MultipartHttpServletRequest request) {
        return attachmentService.upload(request);
    }

    @GetMapping("/download/{id}")
    public void getFileFromSystem(@PathVariable Integer id, HttpServletResponse response) {
        attachmentService.download(id, response);
    }

}
