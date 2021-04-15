import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class TCPConnection1 extends Thread {

	private Main ref;

	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Waiting connection...");

			Socket socket = server.accept();
			System.out.println("Connected");

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(isr);

			while (true) {

				String dataReceived = breader.readLine();
				Gson gson = new Gson();

				Message c = gson.fromJson(dataReceived, Message.class);

				ref.notify(c, this);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMain(Main main) {
		this.ref = main;
	}

}
