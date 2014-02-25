package ajeetmurty.reference.java.net.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	private final int port = 19999;

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		logp.info("start");
		listenForRequests();
		logp.info("stop");
	}

	private void listenForRequests() {
		try {
			logp.info("input port: " + port);
			serverListen(port);
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
	}

	private void serverListen(int port) throws IOException {
		logp.info("this machine: " + java.net.InetAddress.getLocalHost().getHostName());
		logp.info("creating socket on port: " + port);
		ServerSocket welcomeSocket = new ServerSocket(port);
		logp.info(String.format("listening on port (to stop use CTRL-C): %1$s ...", port));
		boolean looper = true;
		while (looper) {
			Socket connectionSocket = welcomeSocket.accept();
			logp.info("connection established!");
			looper = false;
			dumpSocket(connectionSocket);
			connectionSocket.close();
			welcomeSocket.close();
			logp.info("connection closed successfully!");
		}
	}

	private void dumpSocket(Socket connectionSocket) throws UnsupportedEncodingException, IOException {
		if (connectionSocket != null) {
			StringBuffer buff = new StringBuffer();
			buff.append("\n\t connection info:");
			buff.append("\n\t\t host address: " + connectionSocket.getInetAddress().getHostAddress());
			buff.append("\n\t\t host name: " + connectionSocket.getInetAddress().getHostName());
			buff.append("\n\t\t fqdn: " + connectionSocket.getInetAddress().getCanonicalHostName());
			buff.append("\n\t\t is Loopback address: " + connectionSocket.getInetAddress().isLoopbackAddress());
			buff.append("\n\t\t is local address (private n/w RFC-1918): " + connectionSocket.getInetAddress().isSiteLocalAddress());
			buff.append("\n\t\t is multicast address: " + connectionSocket.getInetAddress().isMulticastAddress());
			buff.append("\n\t\t is reachable (timeout:5000ms, conn type:ICMP ECHO/TCP Port 7): " + connectionSocket.getInetAddress().isReachable(5000));
			logp.info(buff.toString());
		} else {
			logp.error("socket object is null.");
		}
	}
}
