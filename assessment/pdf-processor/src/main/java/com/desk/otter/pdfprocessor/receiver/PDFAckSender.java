package com.desk.otter.pdfprocessor.receiver;

import com.desk.otter.pdfprocessor.entity.FilePDF;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PDFAckSender {

    Logger logger = LoggerFactory.getLogger("PDFUploader");
    @Autowired
    Queue queue;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    public PDFAckSender(AmqpTemplate amqpTemplate)
    {
        this.amqpTemplate = amqpTemplate;
    }

    public void produce (String ack)
    {

        amqpTemplate.convertAndSend("ackQueue", ack);

    }
}
