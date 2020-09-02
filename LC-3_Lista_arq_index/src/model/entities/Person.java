package model.entities;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Person implements Serializable{
	protected static final long serialVersionUID = 1L;
	private Integer rg;
	private String name;
	private String dateBirth;
	
	public Person() {
		
	}
	
	public Person(Integer rg, String name, String dateBirth) {
		if(rg.toString().length() <= 8) {
			this.rg = rg;
		} else {
			throw new IllegalArgumentException("RG inválido");
		}
		this.name = name;
		this.dateBirth = dateBirth;
	}

	public Integer getRg() {
		return rg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public boolean saveToFile(RandomAccessFile file) throws IOException{
		file.seek(file.length());
		file.writeInt(rg);
		file.writeUTF(name);
		file.writeUTF(dateBirth);
		return true;
	}
	
	@Override
	public String toString() {
		return rg + ";" + name + ";" + dateBirth;
	}
	
}
