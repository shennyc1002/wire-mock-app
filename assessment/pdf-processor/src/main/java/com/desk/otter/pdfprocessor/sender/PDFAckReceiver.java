package com.desk.otter.pdfprocessor.sender;

import com.desk.otter.pdfprocessor.entity.FilePDF;
import com.desk.otter.pdfprocessor.receiver.PDFReceiver;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PDFAckReceiver {

    Logger logger = LoggerFactory.getLogger(PDFReceiver.class);
    public void processMessage(String message)
    {
        logger.debug("Acknowledgement received "+message);

    }
}
