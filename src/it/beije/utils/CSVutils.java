package it.beije.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSVutils {
	
	public static List<String> getFileAsStrings(String pathFile) throws IOException {
		List<String> content = new ArrayList<String>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(pathFile));
			
			while(reader.ready()) {
				content.add(reader.readLine());
			}
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			throw ioEx;
		} finally {
			try {
				reader.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
		
		return content;
	}
	
	public static void writeRowsInFile(String pathFile, String... newRows) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile));
		for (String r : newRows) {
			writer.write(r);
			writer.newLine();
		}

		writer.close();
	}

	public static void appendRowsInFile(String pathFile, String... newRows) throws IOException {
		File file = new File(pathFile);
		List<String> oldRows = new ArrayList<String>();
		if (file.exists()) {
			oldRows = getFileAsStrings(pathFile);
		}
		System.out.println(oldRows);
		
		oldRows.addAll(Arrays.asList(newRows));
		writeRowsInFile(pathFile, oldRows.toArray(new String[0]));
		
//		String[] totalRows = new String[oldRows.size() + newRows.length];
//		int i;
//		for (i = 0; i < oldRows.size(); i++) {
//			String r = oldRows.get(i);
//			totalRows[i] = r;
//		}
//		for (String r : newRows) {
//			totalRows[i] = r;
//			i++;
//		}
		
//		BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile));
//		for (String r : oldRows) {
//			writer.write(r);
//			writer.newLine();
//		}
//		for (String r : newRows) {
//			writer.write(r);
//			writer.newLine();
//		}
//
//		writer.close();
	}
	
}
