package com.desk.otter.pdfprocessor.sender;

import com.desk.otter.pdfprocessor.entity.FilePDF;
import com.desk.otter.pdfprocessor.queueconfig.QueueConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PDFUploader {

    Logger logger = LoggerFactory.getLogger("PDFUploader");
    @Autowired
    Queue queue;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    public PDFUploader(AmqpTemplate amqpTemplate)
    {
        this.amqpTemplate = amqpTemplate;
    }

    public void produce (FilePDF filePDF)
    {
        System.out.println("inside filePDF");
        logger.error("Inside filePDF");
        try {
            logger.error("From Sender "+filePDF.getBlueprintId());
            amqpTemplate.convertAndSend(queue.getName(), new ObjectMapper().writeValueAsString(filePDF));

        } catch (JsonProcessingException e) {
           logger.error("No Queue Found");
        }
    }
}
