package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cryptography {
	
	private List<String> list = new ArrayList<>();
	private List <String> encriptList = new ArrayList<>();
	
	public Cryptography() {
		
	}

	public List<String> getList() {
		return list;
	}
	
	public List<String> getEncryptList() {
		return encriptList ;
	}
	
	public void addList(String line) {
		list.add(line);
	}
	
	public void addEncriptList(String line) {
		encriptList.add(line);
	}
	

	public void readFile() {
		String path = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista2/files/fileText.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while (line != null) {
				addList(line);
				line = br.readLine();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void rot13() {
		String path = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista2/files/encrypt.txt";
		String encrypt = null;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(String line : list) {
				for(int i = 0; i < line.length(); i++) {
					char character = line.charAt(i);
					if(character >= 'a' && character <= 'm') {
						character +=13;
					} else if(character >= 'A' && character <= 'M') {
						character +=13;
					} else if(character >= 'n' && character <= 'z') {
						character -=13;
					} else if(character >= 'N' && character <= 'Z') {
						character -=13;
					} else if (character == '.') {
						character = '?';
					} else if (character == ',') {
						character = '˜';
					} else if (character == '!') {
						character = '<';
					} else if (character == '?') {
						character = '>';
					} else if (character == ':') {
						character = '[';
					} else if (character == ';') {
						character = ']';
					} else if (character == '(') {
						character = ',';
					} else if (character == ')') {
						character = '.';
					} else if (character == '[') {
						character = '{';
					} else if (character == ']') {
						character = '}';
					} else if (character == '0') {
						character = ')';
					} else if (character == '1') {
						character = '!';
					} else if (character == '2') {
						character = '@';
					} else if (character == '3') {
						character = '#';
					} else if (character == '4') {
						character = '$';
					} else if (character == '5') {
						character = '%';
					} else if (character == '6') {
						character = 'ˆ';
					} else if (character == '7') {
						character = '&';
					} else if (character == '8') {
						character = '*';
					} else if (character == '9') {
						character = '(';
					}
					encrypt = Character.toString(character);
					addEncriptList(encrypt);
					System.out.print(encrypt);
					bw.write(encrypt);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void rot13Decrypt() {
		String path = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista2/files/decrypt.txt";
		String decrypt = null;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(String line : encriptList) {
				for(int i = 0; i < line.length(); i++) {
					char character = line.charAt(i);
					if(character >= 'a' && character <= 'm') {
						character +=13;
					} else if(character >= 'A' && character <= 'M') {
						character +=13;
					} else if(character >= 'n' && character <= 'z') {
						character -=13;
					} else if(character >= 'N' && character <= 'Z') {
						character -=13;
					} else if (character == '.') {
						character = '?';
					} else if (character == ',') {
						character = '˜';
					} else if (character == '!') {
						character = '<';
					} else if (character == '?') {
						character = '>';
					} else if (character == ':') {
						character = '[';
					} else if (character == ';') {
						character = ']';
					} else if (character == '(') {
						character = ',';
					} else if (character == ')') {
						character = '.';
					} else if (character == '[') {
						character = '{';
					} else if (character == ']') {
						character = '}';
					} else if (character == '0') {
						character = ')';
					} else if (character == '1') {
						character = '!';
					} else if (character == '2') {
						character = '@';
					} else if (character == '3') {
						character = '#';
					} else if (character == '4') {
						character = '$';
					} else if (character == '5') {
						character = '%';
					} else if (character == '6') {
						character = 'ˆ';
					} else if (character == '7') {
						character = '&';
					} else if (character == '8') {
						character = '*';
					} else if (character == '9') {
						character = '(';
					}
					decrypt = Character.toString(character);
					System.out.print(decrypt);
					bw.write(decrypt);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
