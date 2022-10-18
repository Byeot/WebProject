package cs.dit.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)// 
			throws ServletException, IOException{
		
				BoardDao dao = new BoardDao();
			
				
				int p = 1;
				int numOfRecord = 10;
				
				
				String page_ = request.getParameter("p");
				
				
				
				if(page_ != null)
					p = Integer.parseInt(page_); 
					
					
				
				ArrayList<BoardDto> dtos = dao.list(p,numOfRecord);
				int count = dao.recordCount();
				
				
				
				int startNum = p - (p-1)%5;
				int lastNum = (int)(Math.ceil((float)count/(float)numOfRecord));
				
				
				System.out.println("현재페이지:" +p);
				System.out.println("시작페이지:" +startNum);
				System.out.println("마지막페이지:"+lastNum);
				
				//5. 이 페이지의 저장소인 requestScope에 저장하시오.
				request.setAttribute("dtos",dtos);
				request.setAttribute("lastNum",lastNum);//마지막페이지숫자
				request.setAttribute("startNum",startNum);//시작될 페이지숫자
				request.setAttribute("p",p);//현재페이지
				

	}

}
