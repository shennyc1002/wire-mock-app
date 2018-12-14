package com.desk.otter.pdfprocessor.controller;


import com.desk.otter.pdfprocessor.entity.FilePDF;
import com.desk.otter.pdfprocessor.sender.PDFUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    @Autowired
    PDFUploader pdfUploader;

    @PostMapping(value="/send")
    public void send (@RequestBody FilePDF filePDF)
    {

        filePDF.setCreateTime(new Date());
        pdfUploader.produce(filePDF);

    }
}
