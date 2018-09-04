package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import FTPServer.FTPServer;
import database.Notice.Notice;
import database.Notice.NoticeDAO;
import database.User.User;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/Write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session=request.getSession();
			
			MultipartRequest multi=new MultipartRequest(request, session.getServletContext().getRealPath("/"),1024*1024*10,"utf-8",new DefaultFileRenamePolicy());
			
			
			NoticeDAO dao=NoticeDAO.createNoticeDAO();
			Notice notice=new Notice();
			
			notice.setNotice_title(multi.getParameter("title"));
			notice.setNotice_contents(multi.getParameter("content"));
			notice.setMember_id(((User)session.getAttribute("login")).getId());
			notice.setFile_name(multi.getFilesystemName("upload"));
			
			int noticeNum=dao.insertNotice(notice);
			
			FTPServer ftp=new FTPServer();
			ftp.makeDir(Integer.toString(noticeNum));
			ftp.upload(multi.getFile("upload"), multi.getFilesystemName("upload"));
			response.sendRedirect("index.jsp");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
