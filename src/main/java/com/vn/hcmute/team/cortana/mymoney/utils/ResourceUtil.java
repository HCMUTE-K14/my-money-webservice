package com.vn.hcmute.team.cortana.mymoney.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceUtil {
	public static final Log LOG=LogFactory.getLog(ResourceUtil.class);
	
	public static Properties getConfigProperties(){
		String path=ResourceUtil.class.getClassLoader().getResource("application.properties").getPath();
		
		Properties properties = null;

		try {

			InputStream input = new FileInputStream(path);

			properties=new Properties();
			properties.load(input);
		} catch (IOException e) {
			LOG.info("Cannot find file");
		}

		return properties;
	}
}
