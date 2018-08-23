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
			if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
				client.disconnect();
				throw new IOException("IOException in connecting to FTP Server");
			}
			client.login(PrivateData.ID, PrivateData.PASSWORD);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.enterLocalPassiveMode();
			client.changeWorkingDirectory("/HDD1/web/NoticeBoard");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static FTPServer newInstance() {
		return (instance == null) ? new FTPServer() : instance;
	}

	public void upload(File file, String uploadPath) throws IOException {
		try (InputStream input = new FileInputStream(file)) {
			this.client.storeFile(uploadPath, input);
		}
	}

	public void changeDir(String dir) throws IOException {
		client.changeWorkingDirectory("/HDD1/web/NoticeBoard" + (dir.equals("") ? "" : "/" + dir));
	}

	public void makeDir(String dir) throws IOException {
		client.makeDirectory(dir);
		changeDir(dir);
	}

	public InputStream download(String filePath) throws IOException {
		return client.retrieveFileStream(filePath);
	}
}
