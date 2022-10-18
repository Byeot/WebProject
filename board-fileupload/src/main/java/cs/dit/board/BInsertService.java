package cs.dit.board;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class BInsertService implements BoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//7. insertForm 에서 입력한 subject, content, writer 를 받아와 알맞는 변수에 입력하세요.
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String filename = null;
		String dir = null;
		
		String contentType = request.getContentType();
		System.out.println(contentType);
		
		if(contentType != null && contentType.startsWith("multipart/")) {
			dir = request.getServletContext().getRealPath("/uploadfiles");
		}
		
		//업로드할 폴더가 존재하지 않으면 폴더를 생성
		File f= new File(dir);	
		if(!f.exists()) {
			f.mkdir();
		}
		//request.getParts() 메서드를 통해 여러개의 Part를 Collection에 담아 리턴
		Collection<Part> parts = request.getParts();
		
		for(Part p :parts) {
			if(p.getHeader("Content-Disposition").contains("filename==")) {
				if(p.getSize()>0) {
					filename = p.getSubmittedFileName();//업로드한 파일이름을 리턴하는 코드
					String filepath = dir + File.separator + filename;//dir에 넣어주는 코드
					p.write(filepath);//실제경로에 파일저장
					p.delete();
				}
			}
		}
		
		BoardDto dto = new BoardDto(0, subject, content, writer,null,filename); 
		BoardDao dao = new BoardDao();
		dao.insert(dto);
		
	}

}
