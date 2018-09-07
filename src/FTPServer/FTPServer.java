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


	FTPClient client;
	public PrintWriter write;
	public FTPServer() {
		client = new FTPClient();
		try {
			client.connect(PrivateData.URL);

			write=new PrintWriter(new File("C:\\Users\\seungmun\\Desktop\\Study\\Programing\\Language\\JSP\\NoticeBoard\\test.log"));
			client.addProtocolCommandListener(new PrintCommandListener(write));
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
	public void close() throws IOException{
			client.logout();
			client.disconnect();
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
