package bank.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	HashMap<String, Controller> map = null; //키값, 객체의 주소
	@Override 
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		map = new HashMap<String ,Controller>();
		map.put("/join.do", new JoinController());
		map.put("/login.do", new LoginController());
		map.put("/deposit.do", new DepositController());
		map.put("/withdrawal.do", new WithdrawalController());
		map.put("/query.do", new QueryController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		
		Controller cont = map.get(path);
		cont.execute(req, resp);
	}
}
