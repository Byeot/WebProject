package cs.dit.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







@WebServlet("*.do")
public class membercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do"));//주소의 위치를 알려주기위해
		String viewPage = null;
		
		if(com!= null&&com.trim().equals("list")) {
			memberService service = new MListService();
			service.execute(request, response);
			viewPage = "/WEB-INF/view/list.jsp";		
			
			
		}else if(com!= null&&com.trim().equals("insertForm")) {
			viewPage = "/WEB-INF/view/insertForm.jsp";
		
		}else if(com!= null&&com.trim().equals("index")) {
			viewPage = "/WEB-INF/view/index.jsp";
			
		}else if(com!= null&&com.trim().equals("insert")) {
			memberService service = new MInsertService();
			service.execute(request, response);
			viewPage = "/WEB-INF/view/list.do";
			
		}else if(com!= null&&com.trim().equals("updateForm")) {
			System.out.println("if updateform");
			memberService service = new MUpdateViewService();//상위 인터페이스 부모 자식
			service.execute(request, response);
			viewPage = "/WEB-INF/view/updateForm.jsp";
			
		}else if(com!= null&&com.trim().equals("update")) {
			memberService service = new MUpdateService();
			service.execute(request, response);
			viewPage = "/WEB-INF/view/list.do";
			
		}else if(com!= null&&com.trim().equals("delete")) {
			memberService service = new MdeleteService();
			service.execute(request, response);
			viewPage = "/WEB-INF/view/list.do";
		
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
