package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import model.entities.Person;


public class Main {
	
	public static List <Long> listId = new ArrayList<>();
	public static String indexPath = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista_arq_index/files/index.bin";
	
	
	//lendo arquivo texto, 
	//transformando em objeto Person,
	//convertendo arquivo texto em arquivo binario
	public static void convertFile(String textPath, String binPath) throws IOException {
		File in = new File(textPath);
		Scanner sc = new Scanner(in);	
		int count = 0;
		try(RandomAccessFile out = new RandomAccessFile(new File(binPath), "rw")){
			out.setLength(0);
			out.writeInt(0);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] word = line.split(";");
				Integer rg = Integer.parseInt(word[0]);
				String name = word[1];
				String dateBirth = word[2];
				Person p = new Person(rg, name, dateBirth);
				long next = out.getFilePointer();
				System.out.println(p.getRg()+ " na posição " + next + ".");
				createIndex(indexPath, next, p.getRg());
				p.saveToFile(out);
				count++;
			}
			sc.close();
			out.seek(0);
			out.writeInt(count);
			out.close();
		}catch(FileNotFoundException e) {
			System.out.println("Aconteceu um problema: " + e.getMessage());
		}
	}
	
	//criação de indice para o arquivo eadicionando o indice em uma lista
	public static void createIndex(String indexPath, long next, Integer rg) throws IOException {
		try(RandomAccessFile index = new RandomAccessFile(new File(indexPath), "rw")){
			index.writeLong(next);
			index.writeChars(";");
			index.writeInt(rg);
			listId.add(next);
			
		} catch(FileNotFoundException e) {
			System.out.println("Aconteceu um problema: " + e.getMessage());
		}
	}
	
	//criação de menu
	public static int menu(Scanner sc) {
		System.out.println();
		System.out.println("Busca de pessoas");
		System.out.println("---------------------");
		System.out.println("1 - Busca sequencial");
		System.out.println("2 - Busca por indice");
		System.out.println("0 - Sair");
		int opc = Integer.parseInt(sc.nextLine());
		return opc;
	}
	
	//pesquisa sem utilização de indice
	public static Person searchPeopleSeq(RandomAccessFile out, Integer rg) throws IOException {
		out.seek(Integer.BYTES);
		boolean endFile = false;
		Person p = null;
		Integer id;
		String name;
		String dateBirth;
		try {
			while(!endFile) {
				id = out.readInt();
				name = out.readUTF();
				dateBirth = out.readUTF();
				p = new Person(id, name, dateBirth);
				if(p.getRg().toString().contains(rg.toString())) {
					return p;
				}
			}
		} catch(EOFException e) {
			p = null;
			endFile = true;
		}
		return p;
	}
	
	//pesquisa utilizando o indice
	public static Person searchPeopleIndex(RandomAccessFile out, long next) throws IOException {
		try {
			out.seek(next);
			Person p = null;
			for(long id : listId) {
				if(id == next) {
					Integer rg = out.readInt();
					String name = out.readUTF();
					String dateBirth = out.readUTF();
					p = new Person(rg, name, dateBirth);
				}
			}
			return p;
			
		}catch(IOException e) {
			System.out.println("Erro na leitura: " + e.getMessage());
			return null;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		String textPath = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista_arq_index/files/in.txt";
		String binPath = "/Users/lucasmourapereira/eclipse-workspace/LC-3_Lista_arq_index/files/out.bin";
		convertFile(textPath, binPath);
		System.out.println("Arquivo binário criado com sucesso!");
		
		Scanner sc = new Scanner(System.in);
		int opc;
		Person search = null;
		
		try(RandomAccessFile out = new RandomAccessFile(new File(binPath), "rw")){

			opc = menu(sc);
			switch(opc) {
			case 1: 
				System.out.println("Busca Sequencial:");
				System.out.println("---------------------");
				System.out.println("Digite o RG da pessoa que procura: ");
				Integer rg = sc.nextInt();
				search = searchPeopleSeq(out, rg);
				if(search != null) {
					System.out.println(search);
				} else {
					System.out.println("Não encontrado");
				}
				break;
			case 2:
				int qtdPeople = out.readInt();
				System.out.println("Busca por indice:");
				System.out.println("---------------------");
				System.out.println("Você tem " + qtdPeople + " pessoas cadastradas no arquivo.");
				System.out.println("Digite o indice da pessoa que procura: ");
				long next = Long.parseLong(sc.nextLine());
				search = searchPeopleIndex(out, next);
				if(search != null) {
					System.out.println(search);
				} else {
					System.out.println("Não encontrado");
				}
				out.close();
				break;
			}while(opc !=0);
		} catch(IOException e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		}
		sc.close();

	}

}
