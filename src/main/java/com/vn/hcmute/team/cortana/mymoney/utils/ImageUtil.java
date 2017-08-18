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
	public static void main(String[] args) throws ParseException{
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//
//		int mYear = calendar.get(Calendar.YEAR);
//		int mMonth = calendar.get(Calendar.MONTH)+1;
//		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateToday = "01-12-2017 00:00:00";
		String dateTommorrow="00-02-2017 23:59:59";
		
		String between="18-08-2017 19:59:12";
		
		Date date1= sdf.parse(dateToday);
		Date date2 = sdf.parse(dateTommorrow);
		Date date3 = sdf.parse(between);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		long s1=calendar.getTimeInMillis();
		System.out.println("Calender - Time in milliseconds 1: " + calendar.getTimeInMillis());
		calendar.setTime(date2);
		long s2=calendar.getTimeInMillis();
		System.out.println("Calender - Time in milliseconds 2: " + calendar.getTimeInMillis());
		calendar.setTime(date3);
		long s3=calendar.getTimeInMillis();
		System.out.println(s3>=s1 && s3<=s2 ? "true":"false");
	}
}
