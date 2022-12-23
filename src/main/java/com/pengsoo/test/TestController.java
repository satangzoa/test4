package com.pengsoo.test;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pengsoo.test.dao.IDao;
import com.pengsoo.test.dto.MemberDto;

@Controller
public class TestController {
	
	@Autowired
	private SqlSession sqlSession;
	
//	@RequestMapping(value="/")
//	public String goList() {
//		return "redirect:join";
//	}

	@RequestMapping(value = "join")//join 화면만 보여준다.
	public String join() {
		
		return "join";
	}
	                                                                                                                                                                                                                                                                                 
	@RequestMapping(value = "joinMember")//파라미터보내준다.
	public String joinMember(HttpServletRequest request, Model model) {
		                                                            
		IDao dao = sqlSession.getMapper(IDao.class);//IDao로 반환
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		dao.memberJoin(mid, mpw, mname, memail);//반환하는 데이터타입이 없다.db에 회원정보 삽입
		
		model.addAttribute("memberID", mid);//memberID임의로 정해도 된다
		
		return "joinOk";
	}
	
	@RequestMapping("memberList")
	public String membeList(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		ArrayList<MemberDto> dtos = dao.memberList();//dtos=적당히 작명 dtos에 저장 통째로 보낸다
		
		//memberDtos.get(0).getMname();//첫번째 가입회원의 이름 추출
		
	    model.addAttribute("memberList", dtos);//dtos에 통째 실어서 보낸다

		
		return "memberList";
	}
	

	@RequestMapping(value = "idSearch")//idSearch 화면만 보여준다.
	public String idSearch() {
		
		return "idSearch";
	}
	
	@RequestMapping(value = "idOk")//idSearch에서 idOk 실행할 맵핑
	public String idOk(HttpServletRequest request, Model model) {
		
		String searchId = request.getParameter("searchId");
		IDao dao = sqlSession.getMapper(IDao.class);
		
		MemberDto mdto = dao.searchIdOk(searchId);
		model.addAttribute("searchRs", mdto);
		
		System.out.println(mdto.getMdate());
		
		return "idOk";
	}
	
	
}
