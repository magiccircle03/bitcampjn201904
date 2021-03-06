package com.bitcamp.mm.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitcamp.mm.member.domain.MemberInfo;
import com.bitcamp.mm.member.domain.RequestMemberRegist;
import com.bitcamp.mm.member.service.MemberDeleteService;
import com.bitcamp.mm.member.service.MemberListService;
import com.bitcamp.mm.member.service.MemberRegService;

// /rest-api/members      GET     -> 전체 리스트, 페이징 처리된 리스트
// /rest-api/members/{id} GET     -> 회원 한명의 정보 반환
// /rest-api/members      POST    -> 회원 정보를 저장
// /rest-api/members/{id} PUT     -> 회원 정보를 수정
// /rest-api/members/{id} DELETE  -> 회원 정보를 삭제

@Controller //   
@RequestMapping("/rest-api/members")
public class MemberRestApiController {
	
	@Autowired
	private MemberListService listService;
	
	@Autowired
	private MemberRegService regService;
	
	@Autowired
	private MemberDeleteService deleteService;
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<MemberInfo> getAllList(){		
		return listService.getAllList();
	}
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String regMember(
			RequestMemberRegist regRequest,
			MultipartHttpServletRequest request
			) {
		
			//System.out.println("check : " + regRequest);
			//System.out.println("check : " + request.getParameter("uName"));
		int result = regService.memberInsert(request, regRequest);
		
		return result>0?"success":"fail";
	}
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteMember(
				@PathVariable("id") int idx
			) {
		
		//System.out.println(idx);
		
		int result = deleteService.memberDelete(idx);
		
		return result>0?"success":"fail";
	}
	
	
	
	
	
	
	
	
	
	
}














