package cs.dit.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MListService implements memberService  {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao dao = new MemberDao();
		
		
		int p = 1; //페이지 초기값
		int numOfRecord = 10;
		
		String page_ = request.getParameter("p");
		
		if(page_ != null)
			p = Integer.parseInt(page_); 
			
			
		
		ArrayList<MemberDto> dtos = dao.list(p,numOfRecord);//현제 페이지 번호를 매개변수로 전달
		int count = dao.recordCount();
		
		
		
		int startNum = p - (p-1)%5; //화면에 출력될 첫 페이지 번호 값 계산
		//마지막 출력 페이지 번호를 얻기 위해 10으로 나누고 올림하기
		int lastNum = (int)(Math.ceil((float)count/(float)numOfRecord));
		
		
		System.out.println("현재페이지:" +p);
		System.out.println("시작페이지:" +startNum);
		System.out.println("마지막페이지:"+lastNum);
		
		//request scope에 저장 -> el에 사용
		request.setAttribute("dtos", dtos);
		request.setAttribute("dtos",dtos);
		request.setAttribute("lastNum",lastNum);
		request.setAttribute("startNum",startNum);
		request.setAttribute("p",p);
		
		
		
		
		
	}
	

}
