package de.umr.ds.task2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		// TODO Task 2b)

		Executors.newFixedThreadPool(4);

		ServerSocket serverSocket = new ServerSocket(5555);
		Socket client = serverSocket.accept();

		DataInputStream input = new DataInputStream(client.getInputStream());
		DataOutputStream output = new DataOutputStream(client.getOutputStream());

		String str = (String) input.readUTF();
		output.writeUTF(str);
		System.out.println(str);

		output.close();
		input.close();
	}
}
