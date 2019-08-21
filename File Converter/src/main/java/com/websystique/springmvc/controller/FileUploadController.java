package com.websystique.springmvc.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.MultiFileBucket;
import com.websystique.springmvc.model.singleConv;
import com.websystique.springmvc.util.FileValidator;
import com.websystique.springmvc.util.MultiFileValidator;

@Controller
public class FileUploadController {

	private static String UPLOAD_LOCATION="C:/mytemp/";

	@Autowired
	FileValidator fileValidator;

	@Autowired
	MultiFileValidator multiFileValidator;

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@InitBinder("multiFileBucket")
	protected void initBinderMultiFileBucket(WebDataBinder binder) {
		binder.setValidator(multiFileValidator);
	}
	
	@InitBinder("singleConv")
	protected void initBindersingleConv(WebDataBinder binder) {
		
	}

	@RequestMapping(value = { "/", "/layout" }, method = RequestMethod.GET)  	//OPEN THE FIRST PAGE 
	public String welcome (ModelMap model) {
		return "layout";
	}

	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)			//OPENS OUR DESIRED PAGE-1	
	public String getlayoutPage(ModelMap model) {
		return "welcome";	//			
	}
	
	@RequestMapping(value = "/Signup", method = RequestMethod.GET)   //Code to open expected page-2  link the value to the value in layout.jsp in the portion i wrote bla bla and use GET
	public String getsignupPage(ModelMap model) {
		return "Signup";   // changed here bla bla blaaaaa return the corresponding jsp
	}
	
//	@RequestMapping(value = "/post", method = RequestMethod.GET)   //Code to open expected page-2  link the value to the value in layout.jsp in the portion i wrote bla bla and use GET
//	public String getpostPage(ModelMap model) {
//		return "post";   //This will work? idk
//	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) //added this too
	public String getloginPage(ModelMap model) {
		return "login";  
	}
	
	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET) // works fine
	public String getSingleUploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "singleFileUploader";
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST) //works fine
	public String singleFileUpload(@Valid FileBucket fileBucket,
			BindingResult result, ModelMap model) throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "singleFileUploader";
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileBucket.getFile();

			// Now do something with file...
			String a = fileBucket.getFile().getOriginalFilename();
				    if(a.endsWith(".png"))
			
			           {
				         a=fileBucket.getFile().getName()+".png"; 
			           }
			    if(a.endsWith(".pdf"))
		
		           {
			         a=fileBucket.getFile().getName()+".pdf"; 
		           }
			    if(a.endsWith(".jpg"))
					
		           {
			         a=fileBucket.getFile().getName()+".jpg"; 
		           }
			
			    selectImage1(fileBucket.getFile());
			    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION +  a));
			String fileName = multipartFile.getOriginalFilename();
			
			File file= new File("C:/mytemp/file.jpg");
	           convertJPGToPDF(file.toString(),"C:/mytemp/PQR"+".pdf");
			
			model.addAttribute("fileName", fileName);
			  
	           
			return "success";
		}
	}
	

	
	
	private void selectImage(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		
	}

	@RequestMapping(value = "/multiUpload", method = RequestMethod.GET) // no idea if it works
	public String getMultiUploadPage(ModelMap model) {
		MultiFileBucket filesModel = new MultiFileBucket();
		model.addAttribute("multiFileBucket", filesModel);
		return "multiFileUploader";
	}

	@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
	public String multiFileUpload(@Valid MultiFileBucket multiFileBucket,
			BindingResult result, ModelMap model) throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return "multiFileUploader";
		} else {
			System.out.println("Fetching files");
			List<String> fileNames = new ArrayList<String>();
			// Now do something with file...
			for (FileBucket bucket : multiFileBucket.getFiles()) {
				FileCopyUtils.copy(bucket.getFile().getBytes(), new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
				fileNames.add(bucket.getFile().getOriginalFilename());
			}

			model.addAttribute("fileNames", fileNames);
			return "multiSuccess";
		}
	}


	@RequestMapping(value =  "/convertFile" , method = RequestMethod.GET)
	
	public String getconvoptionspage(ModelMap model) {
		singleConv converter=new singleConv();
		//String path="C:/mytemp/file.jpg";
		//converter.selectImage(path);
		model.addAttribute("singleConv", converter);
				return "result";
	}
	
	 public void selectImage1(MultipartFile File){
		 

       
     
         
           
      }
	

public static void convertJPGToPDF(String strimg,String desc){ // conversion algorithm
try{
//create document object
Document doc=new Document();
//create pdf writer object to write the document to the output file
PdfWriter.getInstance(doc,new FileOutputStream(desc));
//get a4 paper size
Rectangle r=PageSize.A4;
//read the image
BufferedImage orImg=ImageIO.read(new File(strimg));
//initialize image width and height
int width=orImg.getWidth();
int height=orImg.getHeight();
//resize the image that is bigger than A4 size
if(width>r.getWidth())
width=(int)r.getWidth();
if(height>r.getHeight())
height=(int)r.getHeight();  
//create a blank buffered image
BufferedImage bi=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//create graphic2d object from the buffered image
Graphics2D g2d=bi.createGraphics();
//draw the original image on the buffered image
//so the image is resized to fit the A4 paper size if it is bigger than A4 size
g2d.drawImage(orImg, 0,0,width,height,null);
//store the image data in memory
ByteArrayOutputStream bas=new ByteArrayOutputStream();
ImageIO.write(bi, "png", bas);
//create image from the image data stored in memory
Image img=Image.getInstance(bas.toByteArray());
//centrally align the image on the pdf page
img.setAlignment(Element.ALIGN_CENTER);
//open document
doc.open();
//add image to the document
doc.add(img);
//close the document
doc.close();


}catch(Exception e){e.printStackTrace();}
}
	
}
