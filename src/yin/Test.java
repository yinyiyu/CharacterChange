package yin;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		String file_path = "E:\\Album\\a";
		File file = new File(file_path);
		if(!file.exists()){
			System.out.println("路径不存在");
		}else{
			File[] files  = file.listFiles();
			int i = files.length;
			System.out.println("该路径下包含的文件数为："+i);
			for(File f:files){
				System.out.println(f.getName());
			}
		}
	}
}
