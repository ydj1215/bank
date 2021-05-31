package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.service.Service;

public class WithdrawalController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int money= Integer.parseInt(request.getParameter("money"));
		
		String id = (String)(request.getSession().getAttribute("id"));   //request.getSession()자체가 HttpSession이기 때문에 한꺼번에 처리
		
		int tMoney = Service.getInstance().withdrawal(id,money);
		int Q = Service.getInstance().query(id);
		if(tMoney < 0)
		{
			request.setAttribute("result", "Money is not enough");
		}
		request.setAttribute("money", money); //el로 표현하기 위해서 result 값을 넣자
		request.setAttribute("tMoney", tMoney); 
		HttpUtil.forward(request, response, "/result/withdrawalResult.jsp");
	}

}
