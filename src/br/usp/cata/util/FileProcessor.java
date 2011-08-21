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

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;


public class FileProcessor {
	
	private String fileName;
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
	
	// FIXME achar um metodo melhor para determinar o Encoding
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

	