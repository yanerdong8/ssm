package com.yed.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte['?'];
			int n;
			while ((n = fis.read(b)) != -1) {
				// int n;
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static File getFileFromBytes(byte[] b, String outputFile) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile()) {
			file.delete();
		}

	}
	
	
	/**
	 * 传递文件方法，主要是结合spring的文件上传来使用，文件名中会自动在开始处追加日期字符串以示区分
	 * @param uploadPath 上传文件夹根目录
	 * @param file 待上传文件对象
	 * @return 传递后的目标文件对象
	 * @throws Exception
	 * @author yubin
	 */
	public static File transferFile(String uploadPath, MultipartFile file) throws Exception {
		File uploadFilePath = new File(uploadPath);
		if (!uploadFilePath.exists()) {
			uploadFilePath.mkdirs();
		}
		String date = DateFormatUtil.Format(new Date(), "yyyyMMddHHmmssSSS");

		File finishfile = new File(uploadFilePath.getAbsolutePath() + "/" + date + file.getOriginalFilename());

		file.transferTo(finishfile);

		return finishfile;
	}
}
