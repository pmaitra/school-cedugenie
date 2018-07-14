package com.qts.icam.utility.uploaddownload;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Image;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.utility.Utility;

public class FileUploadDownload {
	
	public static Logger logger = Logger.getLogger(FileUploadDownload.class);

	/**
	 * this method is used for delete directory and files
	 * 
	 * @param path
	 * @return int
	 */
	public int deleteDirectory(String path){
		int returnResult=0;
		try{
			File directory = new File(path);
	    	/*
	    	 * make sure directory exists
	    	 */
			if(directory.isDirectory()){
				deleteFileAndDirectory(directory);
			}
	    	logger.info("Directory deleted Done");
		}catch(Exception e){
			logger.error("Exception in deleteDirectory() method in FileUploadDownload", e);
		}
		return returnResult;
	}
	
	 public void deleteFileAndDirectory(File file) throws IOException{
		    	if(file.isDirectory()){
		    		/*
		    		 * directory is empty, then delete it
		    		 */
		    		if(file.list().length==0){
		    		   file.delete();
		    		   logger.info("Directory is deleted : " + file.getAbsolutePath());
		    		}else{
		    		  /*
		    		   * list all the directory contents
		    		   */
		        	   String files[] = file.list();
		        	   for (String temp : files) {
		        	      /*
		        	       * construct the file structure
		        	       */
		        	      File fileDelete = new File(file, temp);
		        	      /*
		        	       * recursive delete
		        	       */
		        	      deleteFileAndDirectory(fileDelete);
		        	   }
		        	  /*
		        	   * check the directory again, if empty then delete it
		        	   */
		        	   if(file.list().length==0){
		           	     file.delete();
		        	     logger.info("Directory is deleted : " + file.getAbsolutePath());
		        	   }
		    		}
		    	}else{
		    		/*
		    		 * if file, then delete it
		    		 */
		    		file.delete();
		    		logger.info("File is deleted : " + file.getAbsolutePath());
		    	}
		}

	/**
	 * 
	 * @param uploadFile
	 * @param filePath
	 * @param file
	 * @return
	 */
	public int fileUpload(String filePath,CommonsMultipartFile file) {
		int fileSize;
		if (file.getFileItem().get() == null) {
			return -1;
		}
		try {
			File dir = new File(filePath);
			boolean isDirCreated = dir.mkdirs();
			if (isDirCreated)
				logger.debug("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.debug("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			filePath=filePath+file.getOriginalFilename()+ "." + "zip";
			OutputStream fos = new FileOutputStream(new File(filePath));
			// create object of ZipOutputStream from FileOutputStream
			ZipOutputStream zos = new ZipOutputStream(fos);
			/*
			 * To begin writing ZipEntry in the zip file, use void
			 * putNextEntry(ZipEntry entry) method of ZipOutputStream class.
			 * This method begins writing a new Zip entry to the zip file and
			 * positions the stream to the start of the entry data.
			 */

			// ZipEntry(form.getFile().getOriginalFilename());
			ZipEntry ze = new ZipEntry(file.getOriginalFilename());
			zos.putNextEntry(ze);
			// create byte buffer
			byte[] buffer = new byte[1024];
			// ...buffer= form.getFile().getFileItem().get();
			buffer = file.getFileItem().get();
			// this constructor takes byte[] as input
			// ....ByteArrayDataSource rawData= new
			// ByteArrayDataSource(form.getFile().getFileItem().get());
			ByteArrayDataSource rawData = new ByteArrayDataSource(file
					.getFileItem().get());
			// creates a DataHandler object
			DataHandler data = new DataHandler(rawData);
			// Get the InputStream for this object.
			InputStream is = data.getInputStream();
			InputStream in = data.getInputStream();

			/*
			 * After creating entry in the zip file, actually write the file.
			 */
			int len;
			fileSize = in.read(buffer);

			while ((len = is.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			/*
			 * After writing the file to ZipOutputStream, use void closeEntry()
			 * method of ZipOutputStream class to close the current entry and
			 * position the stream to write the next entry.
			 */
			zos.closeEntry();
			// close the InputStream
			is.close();
			// close the ZipOutputStream
			zos.close();
			logger.debug("Done");
			return fileSize;
		} catch (Exception e) {
			logger.error("FROM SERVICE LAYER Error while saving file",e);
			return -1;
		}
	}

	public List<Attachment> uploadDoc(String path, UploadFile uploadFile,
			String resourceId, String strRootPath) {
		String attachmentName = null;
		String attachmentFormat = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		// create directory and sub directory
		File dir = new File(path);
		boolean isDirCreated = dir.mkdirs();
		if (isDirCreated)
			logger.debug("In BackOfficeController submitStaffForm() method: upload file folder location created.");
		else
			logger.debug("In BackOfficeController submitStaffForm() method: upload file folder location already exist.");
		for (CommonsMultipartFile file : uploadFile.getFileData()) {
			Attachment attachment = new Attachment();
			logger.info("XYQ: " + file.getOriginalFilename());
			if (file.getOriginalFilename() != "") {
				/* create a zip file */
				String fileName = file.getOriginalFilename() + "." + "zip";
				/* set actual storage path */
				String filePath = path + fileName;
				/* split root path */
				String s[] = strRootPath.split("/");
				int dotPos1 = filePath.lastIndexOf("/");
				String setRootPath = s[s.length - s.length] + "/";
				String storageName = s[s.length - s.length + 1].trim();
				/* get sub folder */
				String subFolder = (filePath.substring(
						(s[s.length - s.length].length() + 1
								+ s[s.length - s.length + 1].length() + 1),
						dotPos1));
				try {
					/* get file name with extension */
					String str = file.getOriginalFilename();
					int dotPos = str.lastIndexOf(".");
					/* get file format */
					attachmentFormat = str.substring(dotPos + 1);
					/* get file name */
					attachmentName = str.substring(0, dotPos);
				} catch (Exception e) {
					logger.error(
							"Error in BackOfficeController submitStaffForm() method for Exception: ",
							e);
				}
				Utility util = new Utility();
				/* calling fileUpload() method in Utility.java and get file size */
				int fileSize = fileUpload( filePath, file);
				/* set attachment file */
				attachment.setStorageRootPath(setRootPath);
				attachment.setStorageName(storageName);
				attachment.setFolderName(subFolder);
				attachment.setAttachmentName(attachmentName);
				attachment.setAttachmentFormat(attachmentFormat);
				attachment.setAttachmentSize(fileSize);// set file size
				attachment.setResource(resourceId.trim());
				attachment.setAttachmentCode("attachmentCode004");
				attachment.setFolderCode("folderCode002");
				attachment.setAttachmentDescription("attachmentDesc001");
				attachment.setFolderDescription("folderdesc001");
				attachmentList.add(attachment);
			}
		}
		return attachmentList;
	}

	public Attachment uploadFile(String path, UploadFile uploadFile,
			String strRootPath) {
		String attachmentName = null;
		String attachmentFormat = null;
		Attachment attachment = new Attachment();
		// create directory and sub directory
		File dir = new File(path);
		boolean isDirCreated = dir.mkdirs();
		if (isDirCreated)
			logger.debug("In BackOfficeController submitStaffForm() method: upload file folder location created.");
		else
			logger.debug("In BackOfficeController submitStaffForm() method: upload file folder location already exist.");
		CommonsMultipartFile file = uploadFile.getImageFile();
		if (file.getOriginalFilename() != "") {
			/* create a zip file */
			String fileName = file.getOriginalFilename() + "." + "zip";
			/* set actual storage path */
			String filePath = path + fileName;
			/* split root path */
			String s[] = strRootPath.split("/");
			int dotPos1 = filePath.lastIndexOf("/");
			String setRootPath = s[s.length - s.length] + "/";
			String storageName = s[s.length - s.length + 1].trim();
			/* get sub folder */
			String subFolder = (filePath
					.substring((s[s.length - s.length].length() + 1
							+ s[s.length - s.length + 1].length() + 1), dotPos1));
			try {
				/* get file name with extension */
				String str = file.getOriginalFilename();
				int dotPos = str.lastIndexOf(".");
				/* get file format */
				attachmentFormat = str.substring(dotPos + 1);
				/* get file name */
				attachmentName = str.substring(0, dotPos);
			} catch (Exception e) {
				logger.error("Error in BackOfficeController submitStaffForm() method for Exception: ",
						e);
			}
			Utility util = new Utility();
			/* calling fileUpload() method in Utility.java and get file size */
			int fileSize = fileUpload(filePath, file);
			/* set attachment file */
			attachment.setStorageRootPath(setRootPath);
			attachment.setStorageName(storageName);
			attachment.setFolderName(subFolder);
			attachment.setAttachmentName(attachmentName);
			attachment.setAttachmentFormat(attachmentFormat);
			attachment.setAttachmentSize(fileSize);// set file size

			attachment.setAttachmentCode("attachmentCode004");
			attachment.setFolderCode("folderCode002");
			attachment.setAttachmentDescription("attachmentDesc001");
			attachment.setFolderDescription("folderdesc001");

			logger.info("1: " + attachment.getStorageRootPath());
			logger.info("2: " + attachment.getStorageName());
			logger.info("3: " + attachment.getFolderName());
			logger.info("4: " + attachment.getAttachmentFormat());
			logger.info("5: " + attachment.getAttachmentName());

		}
		return attachment;
	}
	
	public Image uploadImage(Image image, String replacedFileName) {
		logger.info("In uploadImage() method of FileUploadDownload");
		String changeFileName = null;
		try {
			File dir = new File(image.getImagepath());
			boolean isDirCreated = dir.mkdirs();
			if (isDirCreated)
 			    logger.info("new directory created for image "+image.getImagepath());
 			else
 				 logger.info(image.getImagepath()+" directory already exist");
			// File path where file will be save
			String filePath = image.getImagepath();
			CommonsMultipartFile file = image.getImageData();
			if (file.getOriginalFilename() != "") {
				// get Original file name with extension
				String strOriginalFileName = file.getOriginalFilename();
				String StrFileDir = filePath;
				// get last index of file.
				int dotPos = strOriginalFileName.lastIndexOf(".");
				// get file format
				String fileFormat = strOriginalFileName.substring(dotPos + 1);
				// change image name with item code
				// changeFileName=item.getItemCode()+"."+fileFormat;
				changeFileName = replacedFileName + "."+ fileFormat;
				int dotPos1 = changeFileName.lastIndexOf(".");
				// file path with changeFileName
				filePath = filePath + changeFileName;
				// get file name without extension
				String fileNameWithoutExtension = changeFileName.substring(0,dotPos1);
				logger.info("fileNameWithoutExtension:::"+ fileNameWithoutExtension);
				// get list of file in a directory
				String[] fileList = dir.list();
				// checking if file exists(checking without extension) then file will be
				// deleted.
				if(fileList!=null && fileList.length!=0){
					for (int i = 0; i < fileList.length; i++) {
						if (fileList[i].startsWith(fileNameWithoutExtension)) {
							File dirFile = new File(StrFileDir + fileList[i]);
							@SuppressWarnings("unused")
							boolean isDirCreated1 = dirFile.delete();
							logger.info("In uploadImage() method of FileUploadDownload:: exist file deleted.");
						} else {
							logger.info("In uploadImage() method of FileUploadDownload:: no file exist.");
						}
					}
				}
				
				OutputStream fos = new FileOutputStream(new File(filePath));
				ByteArrayDataSource rawData = new ByteArrayDataSource(file.getFileItem().get());
				// creates a DataHandler object
				DataHandler data = new DataHandler(rawData);
				// Get the InputStream for this object.
				InputStream is = data.getInputStream();
				// create byte buffer
				byte[] buffer = new byte[1024];
				buffer = file.getFileItem().get();
				int len;
				while ((len = is.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				is.close();
				fos.close();
			}
			image.setReplacedImagedName(changeFileName);
		} catch (Exception e) {
			logger.error(e);
		}
		return image;
	}
	
	

	public String uploadCourseSyllabus(String path, UploadFile uploadFile,String filename, String strRootPath) {
		String totalPath =null;
		String changeFileName = null;
		try{
			for (CommonsMultipartFile file : uploadFile.getFileData()) {
				logger.info("XYQ: " + file.getOriginalFilename());
				if (file.getOriginalFilename() != "") {
					String strOriginalFileName = file.getOriginalFilename();
					String StrFileDir = path;
					// get last index of file.
					int dotPos = strOriginalFileName.lastIndexOf(".");
					// get file format
					String fileFormat = strOriginalFileName.substring(dotPos + 1);
					// change image name with item code
					// changeFileName=item.getItemCode()+"."+fileFormat;
					changeFileName = filename + "."+ fileFormat;
					int dotPos1 = changeFileName.lastIndexOf(".");
					// file path with changeFileName
					StrFileDir = StrFileDir + changeFileName;
					File dir = new File(path);
					boolean isDirCreated = dir.mkdirs();
					if (isDirCreated)
						logger.debug("In BackOfficeController submitStaffForm() method: upload file folder location created.");
					else
						logger.debug("In BackOfficeController submitStaffForm() method: upload file folder location already exist.");
					
					// get file name without extension
					String fileNameWithoutExtension = changeFileName.substring(0,dotPos1);
					logger.info("fileNameWithoutExtension:::"+ fileNameWithoutExtension);
					// get list of file in a directory
					String[] fileList = dir.list();
					// checking if file exists(without extension) then file will be
					// deleted.
					if(fileList!=null && fileList.length!=0){
						for (int i = 0; i < fileList.length; i++) {
							if (fileList[i].startsWith(fileNameWithoutExtension)) {
								File dirFile = new File(path + fileList[i]);
								@SuppressWarnings("unused")
								boolean isDirCreated1 = dirFile.delete();
								logger.info("In uploadImage() method of UploadFileController:: exist file deleted.");
							} else {
								logger.info("In uploadImage() method of UploadFileController:: no file exist.");
							}
						}
					}
					totalPath = StrFileDir;
					OutputStream fos = new FileOutputStream(new File(StrFileDir));
					ByteArrayDataSource rawData = new ByteArrayDataSource(file.getFileItem().get());
					// creates a DataHandler object
					DataHandler data = new DataHandler(rawData);
					// Get the InputStream for this object.
					InputStream is = data.getInputStream();
					// create byte buffer
					byte[] buffer = new byte[1024];
					buffer = file.getFileItem().get();
					int len;
					while ((len = is.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					is.close();
					fos.close();
				}
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return totalPath;
	}
	
	public String downloadFile(HttpServletResponse response,String excelPath,String fileName) {
		String returnMessage = null;
		try{
			logger.info("In downloadExcel() method for excel template: requested file: "+excelPath+fileName);
			if(excelPath!= null){
				excelPath=excelPath+fileName;
				File filedir = new File(excelPath);
				if (!filedir.exists()) {
					returnMessage=null;
				}else{
					/* file download */
					//response.setContentType("application/xls"); /Naimisha 27052017/
					/* 
					 * response.setContentType("APPLICATION/OCTET-STREAM");
					 * response.setContentType("text/html");
					 */
					PrintWriter out = response.getWriter();
					response.setHeader("Content-Disposition","attachment; filename=\"" +fileName+ "\"");
					java.io.FileInputStream fileInputStream = new java.io.FileInputStream(excelPath);
					int i;
					while ((i = fileInputStream.read()) != -1) {
						out.write(i);
					}
					fileInputStream.close();
					out.close();	
					returnMessage="File Available";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in downloadExcel() in Utility for Excel download", e);
		}
		return returnMessage;
	}

	public String uploadExcel(UploadFile uploadFile,String excelPath,String fileName) {
		String returnMessage = null;
		try{
			logger.info("In uploadExcel() method for bulk upload: requested file: "+excelPath+fileName);
				//create directory and sub directory
				File dir = new File(excelPath);
				boolean isDirCreated = dir.mkdirs();
				if (isDirCreated)
	 			    logger.info("new directory created for uploadExcel BulkData "+excelPath);
	 			else
	 				 logger.info(excelPath+" directory already exist");
				CommonsMultipartFile file = uploadFile.getImageFile();
				if(file!=null && file.getOriginalFilename()!=""){
					excelPath=excelPath+fileName;
	    			OutputStream fos = new FileOutputStream(new File(excelPath));
	    			ByteArrayDataSource rawData = new ByteArrayDataSource(file.getFileItem().get());
	    			// creates a DataHandler object
	    			DataHandler data = new DataHandler(rawData);
	    			// Get the InputStream for this object.
	    			InputStream is = data.getInputStream();
	    			// create byte buffer
	    			byte[] buffer = new byte[1024];
	    			buffer = file.getFileItem().get();
	    			int len;
	    			while ((len = is.read(buffer)) > 0) {
	    				fos.write(buffer, 0, len);
	    			}
	    			is.close();
	    			fos.close();
	    			returnMessage=fileName+" Uploaded Successfully";
				}
		}catch(Exception e){
			logger.error("Exception in uploadExcel() in Utility for Excel upload", e);
		}
		return returnMessage;
	}
/**
 * This method convert image(with image path) to  bytearray and then
 * convert into image Base64 format
 * 
 * @param imagePath
 * @return String
 */
	public String getBase64Image(String imagePath) {
		String imageB64 = null;
		try{
			ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
			BufferedImage img=ImageIO.read(new File(imagePath));
			ImageIO.write(img, "jpg", baos);
			baos.flush();
			String base64String=Base64.encode(baos.toByteArray());
			baos.close();
			byte[] bytearray = Base64.decode(base64String);
			imageB64 = javax.xml.bind.DatatypeConverter.printBase64Binary(bytearray);
		}catch(Exception e){
			logger.error("Exception in getBase64Image() method in FileUploadDownload", e);
		}
		/*
		 * should be deleted
		 * @controller
		 * model.addAttribute("imageB64", imageB64);
		 * @JSP
		 * <img src="data:image/jpg;base64, ${imageB64}"  alt="Image not found"  /> 
		 */
		return imageB64;
	}

	public String uploadExcel1(UploadFile uploadFile,String excelPath,String fileName) {
		String returnMessage = null;
		try{
			logger.info("In uploadExcel() method for bulk upload: requested file: "+excelPath+fileName);
				//create directory and sub directory
			System.out.println("kkk"+uploadFile);
				File dir = new File(excelPath);
				boolean isDirCreated = dir.mkdirs();
				if (isDirCreated)
	 			    logger.info("new directory created for uploadExcel BulkData "+excelPath);
	 			else
	 				 logger.info(excelPath+" directory already exist");
				System.out.println("koo"+uploadFile.getImageFile());
				CommonsMultipartFile file = uploadFile.getImageFile();
				System.out.println(file);
				System.out.println(file.getOriginalFilename());
				System.out.println(file.getFileItem());
				if(file!=null && file.getOriginalFilename()!=""){
					//excelPath=excelPath+uploadFile;
					excelPath=excelPath+file.getOriginalFilename();
	    			OutputStream fos = new FileOutputStream(new File(excelPath));
	    			ByteArrayDataSource rawData = new ByteArrayDataSource(file.getFileItem().get());
	    			// creates a DataHandler object
	    			DataHandler data = new DataHandler(rawData);
	    			// Get the InputStream for this object.
	    			InputStream is = data.getInputStream();
	    			// create byte buffer
	    			byte[] buffer = new byte[1024];
	    			buffer = file.getFileItem().get();
	    			int len;
	    			while ((len = is.read(buffer)) > 0) {
	    				fos.write(buffer, 0, len);
	    			}
	    			is.close();
	    			fos.close();
	    			returnMessage=uploadFile+" Uploaded Successfully";
				}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in uploadExcel() in Utility for Excel upload", e);
		}
		return returnMessage;
	}

	public int fileUploadForEmail(String filePath,CommonsMultipartFile file) {
		int fileSize;
		if (file.getFileItem().get() == null) {
			return -1;
		}
		try {
			File dir = new File(filePath);
			boolean isDirCreated = dir.mkdirs();
			if (isDirCreated)
				logger.debug("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.debug("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			filePath=filePath+file.getOriginalFilename();
			OutputStream fos = new FileOutputStream(new File(filePath));
			// create object of ZipOutputStream from FileOutputStream
			//ZipOutputStream zos = new ZipOutputStream(fos);
			/*
			 * To begin writing ZipEntry in the zip file, use void
			 * putNextEntry(ZipEntry entry) method of ZipOutputStream class.
			 * This method begins writing a new Zip entry to the zip file and
			 * positions the stream to the start of the entry data.
			 */

			// ZipEntry(form.getFile().getOriginalFilename());
			//ZipEntry ze = new ZipEntry(file.getOriginalFilename());
			//zos.putNextEntry(ze);
			// create byte buffer
			byte[] buffer = new byte[1024];
			// ...buffer= form.getFile().getFileItem().get();
			buffer = file.getFileItem().get();
			// this constructor takes byte[] as input
			// ....ByteArrayDataSource rawData= new
			// ByteArrayDataSource(form.getFile().getFileItem().get());
			ByteArrayDataSource rawData = new ByteArrayDataSource(file
					.getFileItem().get());
			// creates a DataHandler object
			DataHandler data = new DataHandler(rawData);
			// Get the InputStream for this object.
			InputStream is = data.getInputStream();
			InputStream in = data.getInputStream();

			/*
			 * After creating entry in the zip file, actually write the file.
			 */
			int len;
			fileSize = in.read(buffer);

			while ((len = is.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			/*
			 * After writing the file to ZipOutputStream, use void closeEntry()
			 * method of ZipOutputStream class to close the current entry and
			 * position the stream to write the next entry.
			 */
		//	fos.
			//fos.closeEntry();
			// close the InputStream
			is.close();
			// close the ZipOutputStream
			fos.close();
			logger.debug("Done");
			return fileSize;
		} catch (Exception e) {
			logger.error("FROM SERVICE LAYER Error while saving file",e);
			return -1;
		}


	}
}