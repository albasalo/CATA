package br.usp.cata.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;


public class FileProcessor {
	
	private String fileName;
	// Each element in the list is a line of the text
	private ArrayList<String> text;
	
	public FileProcessor(UploadedFile file) {
		this.fileName = file.getFileName();
		getText(file);
	}

	public String getFileName() {
		return fileName;
	}

	public ArrayList<String> getText() {
		return text;
	}
	
	// FIXME find a better way to guess file encoding
	private Charset guessEncoding(byte[] fileBytes) {
		String[] charsetNames = { "UTF-8", "ISO-8859-1", "US-ASCII", 
				"ISO-8859-15", "cp1252", "UTF-16BE", "UTF-16LE", "UTF-16", "UTF-32" };		
		Charset charset = null;
		
		for(String charsetName : charsetNames) {
			if(!Charset.isSupported(charsetName))
				continue;
			
			charset = Charset.forName(charsetName);
			CharsetDecoder decoder = charset.newDecoder();
			try {
				decoder.decode(ByteBuffer.wrap(fileBytes));
			} catch(CharacterCodingException e) {
				charset = null;
				continue;
			}
			break;
		}
		
		if(charset == null)
			 return Charset.forName("UTF-8");
		
		return charset;
	}
	
	private void getText(UploadedFile file) {
		text = new ArrayList<String>();
		InputStream is = file.getFile();
		
		if(file.getContentType().equals("text/plain")) {
			try {
				byte[] fileBytes = IOUtils.toByteArray(is);
				Charset charset = guessEncoding(fileBytes);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new ByteArrayInputStream(fileBytes), charset));
				
				String line;
		    	while((line = br.readLine()) != null)
		    		text.add(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(file.getContentType().equals("application/pdf")) {
			PDFParser parser = null;
		    String parsedText = "";
		    PDFTextStripper pdfStripper;
		    PDDocument pdDoc = null;
		    COSDocument cosDoc = null;
			
			try {
	            parser = new PDFParser(is);
	        } catch (Exception e) { //FIXME
	            e.printStackTrace();
	        }
	        
	        try {
	            parser.parse();
	            cosDoc = parser.getDocument();
	            pdfStripper = new PDFTextStripper();
	            pdDoc = new PDDocument(cosDoc);
	            parsedText = pdfStripper.getText(pdDoc);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            try {
			    	if (cosDoc != null) cosDoc.close();
			    	if (pdDoc != null) pdDoc.close();
			    } catch (Exception e1) {
	               e.printStackTrace();
	            }
	        }
	        
	        String[] textLines = parsedText.split("\n");
	        
	        for(String line : textLines)
	        	text.add(line);     
		}
	}
	
}

	