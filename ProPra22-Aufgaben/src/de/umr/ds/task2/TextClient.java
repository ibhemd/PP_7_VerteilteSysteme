package de.umr.ds.task2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TextClient {

	public static void main(String[] args) throws IOException {
		// TODO Task 2a)

		Scanner scanner = new Scanner(System.in);
		String sentMessage = scanner.next();

		// erstelle Socket
		//Socket socket = new Socket("dsgw.mathematik.uni-marburg.de", 32823);
		Socket socket = new Socket(InetAddress.getLocalHost(), 5555);

		// zum Nachricht verschicken
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(sentMessage + "\n");

		// zum Nachricht lesen
		DataInputStream input = new DataInputStream(socket.getInputStream());

		System.out.println(input.readUTF());

		output.close();
		input.close();

	}
}