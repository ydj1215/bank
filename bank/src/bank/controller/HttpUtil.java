package bank.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil { //forward 메소드를 사용하기 위해 생성한 클래스
	public static void forward(HttpServletRequest req, HttpServletResponse resp, String path)
	{
		try
		{
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
		catch(Exception e)
		{
			System.out.println("HttpUtill error"+e);
		}
	}
}
