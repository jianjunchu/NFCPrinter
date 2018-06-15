package com.aofei.nfcprinter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class PropUtil {
	private Properties p = new Properties();
	private File file = null; 
	
	public PropUtil(File file) {
		try {
			this.file = file;
			if(!file.exists()){
				file.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file); 
			p.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String key) {
		return p.getProperty(key, "");
	}
	
	public String get(String key,String defaultValue) {
		return p.getProperty(key, defaultValue);
	}
	
	public void set(String key, String value){
		try{
			Writer w = new FileWriter(file);
			p.setProperty(key, value);
			p.store(w, key);
			w.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void clear(){
		this.p = null;
		this.file = null;
	}
}
