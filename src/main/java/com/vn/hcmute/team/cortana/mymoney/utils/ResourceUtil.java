package com.vn.hcmute.team.cortana.mymoney.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceUtil {
    
    public static final Log LOG = LogFactory.getLog(ResourceUtil.class);
    
    public static InputStream getResource(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }
    
    public static Properties getConfigProperties() {
        String path = ResourceUtil.class.getClassLoader().getResource("application.properties")
                  .getPath();
        
        Properties properties = null;
        
        try {
            properties = new Properties();
            properties.load(getResource(path));
        } catch (IOException e) {
            LOG.info("Cannot find file");
        }
        
        return properties;
    }
    
    public static InputStream getDefaultCategory() {
        String path = ResourceUtil.class.getClassLoader().getResource("category_default.json")
                  .getPath();
        try {
            return getResource(path);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Cannot get file");
        }
    }
    
}
