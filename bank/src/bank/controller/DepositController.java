package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.service.Service;

public class DepositController implements Controller{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	int money = Integer.parseInt(request.getParameter("money"));
	
	HttpSession session = request.getSession(); //session 객체에 setAttribute로 id 를 넣어놨다.
	String id = (String)session.getAttribute("id"); //그렇기에 getArrtrubute로 그 id를 가져온다.
	
	Service s = Service.getInstance();
	int totalMoney = s.deposit(id,money); //그 id에 돈을 넣을 것이다.
	
	request.setAttribute("money", money);
	request.setAttribute("tMoney", totalMoney);
	HttpUtil.forward(request, response, "/result/depositResult.jsp");
	}
}
