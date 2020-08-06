package application;

import java.util.Scanner;

import entities.ListInteger;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ListInteger list = new ListInteger();
		
		System.out.print("Enter with quantity of elements you want add in list: ");
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			System.out.print("Enter with the element: ");
			int num = sc.nextInt();
			list.addList(num);
		}
		
		System.out.print("Elements list created: ");
		System.out.println(list.getList());
		
		System.out.println();
		System.out.println("Create file with list elements:");
		list.writeFile();
		
		System.out.println();
		list.readFile();
		System.out.println("Elements add in a new list");
		System.out.println(list.getNewList());
		
		System.out.println();
		System.out.println("Elements ordered in a new list");
		list.orderNewList(list.getNewList());
		
		System.out.println();
		list.writeNewFile();
		System.out.println("Create file with new list elements:");
		
		
		
		sc.close();

	}

}
