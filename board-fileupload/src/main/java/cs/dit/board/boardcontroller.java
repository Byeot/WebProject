package cs.dit.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 50
		
		)

@WebServlet("*.do")//.do라는 이름이 있으면 다 실행하라는 뜻
public class boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.클라이언트 요청 분석
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do"));//주소의 위치를 알려주기위해
		String viewPage = null;
		
		if(com!= null&&com.trim().equals("list")) {
			BoardService service = new BListService();
			service.execute(request, response);//request,response를 담아서 보내줌
			viewPage = "/WEB-INF/view/list.jsp";		
			
		}else if(com!= null&&com.trim().equals("insertForm")) {
			viewPage = "/WEB-INF/view/insertForm.jsp";
		
		}else if(com!= null&&com.trim().equals("index")) {
			viewPage = "/WEB-INF/view/index.jsp";
			
		}else if(com!= null&&com.trim().equals("insert")) {
			BoardService service = new BInsertService();
			service.execute(request, response);
			viewPage = "/WEB-INF/view/list.jsp";
			
		}else if(com!= null&&com.trim().equals("updateForm")) {
			System.out.println("if updateform");
			BoardService service = new BUpdateFormService();//상위 인터페이스 부모 자식
			service.execute(request, response);
			viewPage = "/WEB-INF/view/updateForm.jsp";
			System.out.println("if updateform end");
		
		}else if(com!= null&&com.trim().equals("update")) {
			BoardService service = new BUpdateService();
			service.execute(request, response);
			viewPage = "/WEB-INF/view/list.do";
			
		}else if(com!= null&&com.trim().equals("delete")) {
			BoardService service = new BDeleteService();
			service.execute(request, response);
			viewPage = "/WEB-INF/view/list.do";
			
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);//forward라는 메소드로 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
