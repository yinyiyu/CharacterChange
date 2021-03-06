package yin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class AnsiToUtf8 {
	public void run(String filepath,String type){
		System.out.println();
		ReadFileName tmp = new ReadFileName();
		Vector<String> vec;
		vec = tmp.FileNameOftype(filepath,type);

		for (int i = 0; i < vec.size(); i++) {
			System.out.println(vec.get(i));
			try {
				change(vec.get(i));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void change(String filepath)
			throws UnsupportedEncodingException, IOException {
		BufferedReader buf = null;
		OutputStreamWriter pw = null;
		String str = null;
		String allstr = "";

		// 用于输入换行符的字节码
		byte[] c = new byte[2];
		c[0] = 0x0d;
		c[1] = 0x0a;
		String t = new String(c);

		buf = new BufferedReader(new InputStreamReader(new FileInputStream(
				filepath), "GBK"));
		while ((str = buf.readLine()) != null) {
			allstr = allstr + str + t;
		}

		buf.close();

		pw = new OutputStreamWriter(new FileOutputStream(filepath), "UTF-8");
		pw.write(allstr);
		pw.close();
	}
}

class ReadFileName {

	// 存储遍历获取的所有文件名
	private Vector<String> filelist = new Vector<String>();

	// 遍历指定路径下的所有文件
	// 以ArrayList存储于filelist中
	private void RefreshFileList(String strpath) {
		File dir = new File(strpath);
		File[] files = dir.listFiles();

		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				RefreshFileList(files[i].getAbsolutePath());
			} else {
				// String strFileName =
				// files[i].getAbsolutePath().toLowerCase();
				filelist.add(files[i].getAbsolutePath());
			}
		}
	}

	// 过滤当前目录下的指定后缀名的文件
	// 返回Vector
	public Vector<String> FileNameOftype(String strpath2, String filetype) {
		String strfilename = new String();
		Vector<String> filelistofjava = new Vector<String>();

		// 读取指定路径下的所有文件
		RefreshFileList(strpath2);

		for (int i = 0; i < filelist.size(); i++) {
			strfilename = filelist.get(i).toString();
			strfilename = strfilename.substring(strfilename.length() - filetype.length(),
					strfilename.length());

			if (strfilename.equals(filetype)) {
				filelistofjava.add(filelist.get(i));
			}
		}
		return filelistofjava;
	}
	
}