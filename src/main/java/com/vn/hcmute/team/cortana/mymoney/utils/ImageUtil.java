package com.vn.hcmute.team.cortana.mymoney.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.vn.hcmute.team.cortana.mymoney.Constraint;

public class ImageUtil {
	public static  boolean  uploadImage_Local(String name,String folder,InputStream inputStream) {
		boolean flag = false;
		try {
			File _file=new File(Constraint.LOCATION_STORE_IMAGE+folder);
			_file.mkdirs();
			File file = new File(Constraint.LOCATION_STORE_IMAGE+folder+"/"+name+".png");
			FileOutputStream stream = new FileOutputStream(file);
			try {
				IOUtils.copy(inputStream, stream);
			} finally {
				stream.close();
			}
			flag = true;
		} catch (IOException e) {
			flag = false;
		}
		return flag;
	}
	public static boolean deleteImage(String name,String folder){
		File file = new File(Constraint.LOCATION_STORE_IMAGE+folder+"/"+name+".png");
		
		if(file.exists()){
			file.delete();
			return true;
		}else{
			file=new File(Constraint.LOCATION_STORE_IMAGE+folder+"/"+name+".jpg");
			if(file.exists()){
				file.delete();
				return true;
			}
		}
		return false;
	}
}
