package com.mycompany.myapp.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.myapp.domain.ArticleVO;
import com.mycompany.myapp.domain.MemberDTO;
import com.mycompany.myapp.domain.ReplyVO;
import com.mycompany.myapp.service.ArticleService;
import com.mycompany.myapp.service.MemberService;
import com.mycompany.myapp.service.ReplyService;

@Controller
public class ArticleController {
	@Inject
	ArticleService service;
	
	@Inject
	ReplyService replyService;
	
	@Inject
	MemberService memberService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	// 로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(MemberDTO dto) {	
		return "/login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberDTO dto, HttpServletRequest request, Model model,
			RedirectAttributes rttr) throws Exception {
		// 세션 생성
		HttpSession session = request.getSession();
		
		MemberDTO login = memberService.login(dto);
		
		if(!BCrypt.checkpw(dto.getPw(), login.getPw())) {
			// 세션 저장
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", false);
			// 조건문에 의해 login 값이 null 이라면, msg 라는 정보에 false라는
			// 값이 들어가서 전송된다. 이 값은 다른 페이지로 이동하거나 새로고침을 하면 없어지는 일회용값이다.
	} else {
		System.out.println("login succece");

			session.setAttribute("user", login);
			model.addAttribute("user", login);
			return "redirect:/writeList";
	}
	return "redirect:/login";
	
	/*로그인이 실패하면 어떠한 값도 넘어오지 않으니 null이 되고
	 * 성공하면 매퍼에 있는 쿼리문에 대한 결과가 넘어오게 된다.
	 * 
	 * 조건문을 이용해 이 값이 있는지 없는지를 확인하고,
	 * 이에 맞게 세션 user에 값을 넣어 준다.
	 * user에는 매퍼에 있는 쿼리문의 결과가 들어가 있다.*/ 
	}

	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/login";
	}
		
	// 회원가입
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {	
		return "/register";
	}
	
	// 회원가입 POST
		@RequestMapping(value="/register",method = RequestMethod.POST)
		public String registerPOST(MemberDTO dto,RedirectAttributes redirectAttributes, 
				HttpServletRequest req,Model model) throws Exception {
			HttpSession session=req.getSession();
			if(session.getAttribute("idCheck")!=null) {
				String h_area1 = req.getParameter("h_area1");
				String h_area2 = req.getParameter("h_area2");
				dto.setLocal(h_area1 + " " + h_area2);
				String hashedPw = BCrypt.hashpw(dto.getPw(),BCrypt.gensalt());
				dto.setPw(hashedPw);
				//memberService.register(dto);
				redirectAttributes.addFlashAttribute("msg","REGISTERED");
				
				int result = memberService.idCheck(dto.getId());
				try {
					if(result == 1) {
						return "/login";
					}else if(result == 0) {
						memberService.register(dto);
					}
					// 여기에서 ~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기
					// 존재하지 않는다면 -> register
				} catch(Exception e) {
					throw new RuntimeException();
				}
				return "redirect:/login";
			}else {
			model.addAttribute("msg","오류입니다 다시 진행해주세요");
			return "/dregister";	
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck",method=RequestMethod.POST)
	public int postIdCheck(String id,HttpSession session) throws Exception {
		int result = memberService.idCheck(id);
		session.setAttribute("idCheck", result);
		return result;
	}
		
	//작성 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public String writeView() throws Exception{
		return "/writeView";
	}
	
	// 작성
	@RequestMapping(value = "/writeView", method = RequestMethod.POST)
	public String write(ArticleVO articleVO) throws Exception{
		service.write(articleVO);
		return "redirect:/writeList";
	}
	
	//리스트 조회
	@RequestMapping(value = "/writeList", method = RequestMethod.GET)
	public String list(Model model) throws Exception{
		model.addAttribute("list",service.list());
		return "/writeList";
	}
	
	// 게시판 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(ArticleVO articleVO, Model model) throws Exception{
		model.addAttribute("read", service.read(articleVO.getBno()));
		List<ReplyVO> replyList = replyService.readReply(articleVO.getBno());
		model.addAttribute("replyList", replyList);
		return "/readView";
	}
	
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(ArticleVO articleVO, Model model) throws Exception{
		model.addAttribute("update", service.read(articleVO.getBno()));
		return "/updateView";
	}
	
	// 게시판 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ArticleVO articleVO) throws Exception{
		service.update(articleVO);
		return "redirect:/writeList";
	}
	
	// 게시판 삭제
	@RequestMapping(value = "/deleteView", method = RequestMethod.POST)
	public String delete(ArticleVO articleVO) throws Exception{
		service.delete(articleVO.getBno());
		return "redirect:/writeList";
	}
	
	//댓글 달기
	@RequestMapping(value="/replyWrite", method = RequestMethod.POST)
	public String replyWrite(ReplyVO vo, RedirectAttributes rttr) throws Exception {
		replyService.writeReply(vo);
		rttr.addAttribute("bno", vo.getBno());
		return "redirect:/readView";
	}
}
