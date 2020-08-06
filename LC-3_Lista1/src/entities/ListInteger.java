package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListInteger {
	
	private List<Integer> list = new ArrayList<>();
	private List<String> newList = new ArrayList<>();
	
	public ListInteger() {
		
	}

	public List<Integer> getList() {
		return list;
	}
	
	public List<String> getNewList() {
		return newList;
	}
	
	public void addList(Integer num) {
		list.add(num);
	}
	
	public void removeList(Integer num) {
		list.remove(num);
	}
	
	public void addNewList(String num) {
		newList.add(num);
	}
	
	public void removeNewList(String num) {
		newList.remove(num);
	}
	
	public void writeFile() {
		String path = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista1/files/out.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(Integer num : list) {
				bw.write(num.toString());
				bw.newLine();
			}
			bw.write("FIM");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile() {
		String path = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista1/files/out.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			for(int i = 0; i < list.size(); i++) {
				addNewList(line);
				line = br.readLine();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void orderNewList(List<String>order) {
		Collections.sort(newList);
		System.out.println(newList);
	}
	
	public void writeNewFile() {
	String path = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista1/files/Newout.txt";
	try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
		bw.write("[ ");
		for(String num : newList) {
			bw.write(" " + num + " ");
		}
		bw.write("]");
		bw.newLine();
		for(int i = 0; i < newList.size(); i++) {
			int count = Collections.frequency(newList, newList.get(i));
			String occurrences = Integer.toString(count);
			bw.write("Number " + newList.get(i) + " repeated " + occurrences);
			bw.newLine();
		}
		
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
}
