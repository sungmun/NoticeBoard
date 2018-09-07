package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FTPServer.FTPServer;

/**
 * Servlet implementation class DownLoadServlet
 */
@WebServlet("/DownLoad")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/";
		url+=request.getParameter("post");
		url+="/";
		url+=request.getParameter("file");
		File file=new File(url);
		FTPServer ftp=new FTPServer();
		ftp.changeDir(file.getParent().substring(1));
		InputStream input=ftp.download(file.getName());
		
		String fileType=getServletContext().getMimeType(url);
		
		if(fileType==null) fileType="application.octec-stream";
		
		response.setContentType(fileType);
		
		response.setHeader("Content-Disponsition", "attachment; filename=\""+request.getParameter("file")+"\"");
		
		response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
		ServletOutputStream out=response.getOutputStream();
		int bit;
		while((bit=input.read())!=-1) {
			out.write(bit);
		}
		
		out.flush();
		
		ftp.close();
		out.close();
		input.close();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
