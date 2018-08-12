package FTPServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPServer {
	public static FTPServer instance;
	
	FTPClient client;
	
	private FTPServer() {
		client = new FTPClient();
		client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		try {
			client.connect(PrivateData.URL);
			reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				client.disconnect();
				throw new IOException("IOException in connecting to FTP Server");
			}
			client.login(PrivateData.ID, PrivateData.PASSWORD);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.enterLocalPassiveMode();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static FTPServer newInstance() {
		return (instance==null)?new FTPServer():instance;
	}
	
	public void upload(String fileAllPath, String fileName, String hostPath) throws IOException {
		try (InputStream input = new FileInputStream(new File(fileAllPath))) {
			this.client.storeFile(hostPath + fileName, input);
		}
	}
}