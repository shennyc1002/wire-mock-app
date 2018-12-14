package com.desk.otter.pdfprocessor.receiver;

import com.desk.otter.pdfprocessor.entity.FilePDF;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PDFReceiver {

    @Autowired
     PDFAckSender pdfAckSender;

    Logger logger = LoggerFactory.getLogger(PDFReceiver.class);
    public void processMessage(String message)
    {
        FilePDF filePDF = new FilePDF();

        try {
            filePDF = new ObjectMapper().readValue(message, FilePDF.class);
            logger.error("Received a message from queue");
            processMessage(filePDF);

        } catch (JsonParseException e) {
            logger.error("Bad JSON in message: " + e);
        } catch (JsonMappingException e) {
            logger.error("cannot map JSON to FilePDF: " + message);
            logger.error("Object Parsing error: " + e);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    //Processing the PDF after getting from the queue
    public void processMessage(FilePDF filePDF)
    {
        logger.debug("PDF Processed "+filePDF.getBlueprintId());

        try {
            pdfAckSender.produce("Processed Successfully");
        }
        catch(Exception e)
        {
            logger.debug("Unable to send ack after processing");
        }


    }
}
