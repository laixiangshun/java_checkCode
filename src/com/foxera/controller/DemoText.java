package com.foxera.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foxera.util.CreateImageCode;

@Controller
public class DemoText {
	@RequestMapping(value="/index.html",method=RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "views/index";
	}
	@RequestMapping(value="/index2.html",method=RequestMethod.GET)
	public void index2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			OutputStream out=response.getOutputStream();
			String code=CreateImageCode.randomStr(4);
			HttpSession session=request.getSession();
			session.removeAttribute("code");
			session.setAttribute("code", code);
			CreateImageCode.CreateImg(out, code);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
