package com.desk.otter.pdfprocessor;

import com.desk.otter.pdfprocessor.entity.FilePDF;
import com.desk.otter.pdfprocessor.queueconfig.QueueConfiguration;
import com.desk.otter.pdfprocessor.receiver.PDFReceiver;
import com.desk.otter.pdfprocessor.sender.PDFUploader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PdfProcessorApplicationTests {


	Logger logger = LoggerFactory.getLogger(PdfProcessorApplicationTests.class);


	@Mock
	PDFUploader pdfUploader;

	@Test
	public void contextLoads() {
	}


	@Test
	public void PDFQueueSenderTest()
	{

		logger.error("Inside PDFQueueSender Text");
		FilePDF file = new FilePDF();
		file.setBlueprintId("1234");
		file.setCurrentProcessingPhase("pdf_to_image");
		file.setFileLocation("File Location");
		file.setCreateTime(new Date());
		pdfUploader.produce(file);

	}
}

