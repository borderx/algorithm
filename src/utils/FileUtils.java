package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
	
	public static List<File> listFilesRecursive(File file) {
		if(file == null) {
			throw new NullPointerException("input file is null.");
		}
		List<File> fileList = new ArrayList<File>();
		if(file.isDirectory()) {
			for(File tmp : file.listFiles()) {
				fileList.addAll(listFilesRecursive(tmp));
			}
		}else {
			fileList.add(file);
		}
		return fileList;
	}
	
	public static List<File> listFiles(File file) {
		if(file == null) {
			throw new NullPointerException("input file is null.");
		}
		List<File> fileList = new ArrayList<File>();
		LinkedList<File> stack = new LinkedList<File>();
		//这里若使用栈，则是深度遍历，使用queue则是广度遍历
		stack.push(file);
		while(!stack.isEmpty()) {
			File tmp = stack.pop();
			if(tmp.isDirectory()) {
				for(File t : tmp.listFiles()) {
					stack.push(t);
				}
			}else {
				fileList.add(tmp);
			}
		}
		return fileList;
	}
	
	public static void main(String[] args) {
		File file = new File("G:\\文档");
		List<File> list = listFilesRecursive(file);
		System.out.println(list.size());
		for(File f : list) {
			System.out.println(f.getName());
		}
		
		List<File> list2 = listFiles(file);
		System.out.println(list2.size());
		for(File f : list2) {
			System.out.println(f.getName());
		}
	}
	
}
