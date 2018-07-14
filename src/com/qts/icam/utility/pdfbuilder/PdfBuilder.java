package com.qts.icam.utility.pdfbuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.log4j.Logger;

import com.qts.icam.model.report.Report;
import com.qts.icam.service.impl.inventory.InventoryServiceImpl;

public class PdfBuilder {
	public static Logger logger = Logger.getLogger(PdfBuilder.class);
	public void createPDF(Report report) throws FileNotFoundException {
		
		logger.info("RRRRRRRRR PATH:::::::  "+report.getXsltFilePath()+report.getXsltFileName());
		File outDir = new File(report.getPdfFilePath()); 
		//File xmlfile = new File(report.getXmlFilePath(), report.getXmlFileName()); 
		//File xsltfile = new File(report.getXsltFilePath(), report.getXsltFileName()); 
		//File pdffile = new File(report.getPdfFilePath(), report.getPdfFileName());
		
		// to store output
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
		else
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
		
		OutputStream out = new java.io.FileOutputStream(report.getPdfFilePath()+report.getPdfFileName());
		out = new java.io.BufferedOutputStream(out); 
					
		try{
			// create an instance of fop factory
			final FopFactory fopFactory = FopFactory.newInstance(outDir.toURI());
			// a user agent is needed for transformation
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
				
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out); 
			
			TransformerFactory factory = TransformerFactory.newInstance(); 
			Transformer transformer = factory.newTransformer(new StreamSource(new File(report.getXsltFilePath()+report.getXsltFileName()))); 

			transformer.setParameter("versionParam", "2.0"); 

			Source src = new StreamSource(new File(report.getXmlFilePath()+report.getXmlFileName())); 
			Result res = new SAXResult(fop.getDefaultHandler()); 
			
			transformer.transform(src, res); 
		}
		catch (Exception e){
			logger.error(e);
		}
			finally { 
		}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			} 
		} 
	
	
	
	
	
		
	}


