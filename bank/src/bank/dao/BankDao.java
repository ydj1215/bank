package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bank.vo.Account;

public class BankDao {
	private static BankDao dao = new BankDao();
	private BankDao() {}
	public static BankDao getInstance()
	{
		return dao;
	}
	
	public Connection connect()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","woehddb5555!");
		}
		catch(Exception e)
		{
			System.out.println("MDAO:connect"+e);
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstmt)
	{
		if(pstmt != null)
		{
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{
				System.out.println("Pstmt close error"+e);
			}
		}
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(Exception e)
			{
				System.out.println("Conn close error"+e);
			}
		}
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
	{			
		if(rs != null) //result 에 대한 close
		{
			try
			{
				rs.close();
			}
			catch(Exception e) {
				System.out.println("rs close error"+e);
			}
		}
		close(conn,pstmt); //앞의 2개(conn,pstmt)에 대한 close
	}
	
	public void join(Account account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("insert into account values(?,?,?);");
			pstmt.setString(1,account.getId());
			pstmt.setString(2,account.getPwd());
			pstmt.setString(3,account.getMoney()+"");
			pstmt.executeUpdate(); //insert, delete, update 같은 것들	
		}
		catch(Exception e)
		{
			System.out.println("join error" +e);
		}
		finally
		{
			close(conn,pstmt);
		}
	}
	
	public boolean login(String id, String pwd) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select * from account where id = ? and pwd = ?;");
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			rs = pstmt.executeQuery(); //select
			
			if(rs.next()) //select 할 것이 존재한다, 즉 로그인이 성공했다.
			{
				result = true;
			}
			else //select 할 것이 없다, 즉 로그인이 실패했다.
			{
				result = false;
			}
		}
		catch(Exception e)
		{
			System.out.println("login error" +e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}
		return result;
	}
	
	public int deposit (String id, int money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int moneyDB = 0;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id = ?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				moneyDB = rs.getInt(1);
			}
			//나중에 프로그램을 완성할때는 빈칸 같은 경우를 고려해야한다.
			money = moneyDB + money;
			
			pstmt = conn.prepareStatement("update account set money = ? where id = ?;");
			pstmt.setString(1,money+""); //int인 money 에 아무것도 없는 문자열을 더해서 String으로 만듬
			pstmt.setString(2,id);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("deposit error" +e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}
		return money;
	}
	
	public int withdrawal(String id, int money) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int moneyDB = 0;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id = ?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				moneyDB = rs.getInt(1);
			}
			
			if(money > moneyDB) //돈이 없어서 출금을 못하는 경우
			{
				return -1;
			}
			
			money = moneyDB - money;
			
			pstmt = conn.prepareStatement("update account set money = ? where id = ?;");
			pstmt.setString(1,money+"");
			pstmt.setString(2,id);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("withdrawal error" +e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}
		return money;
	}
	public int query(String id) {
		// TODO Auto-generated method stub
		int money = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id = ?;");
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				money = rs.getInt(1); //첫번째 칼럼(money)만 가져온다
			} //애초에 로그인 한 상태이기 때문에, id가 없을수가 없다. else는 생략
		}
		catch(Exception e)
		{
			System.out.println("login error" +e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}
		
		return money;
	}
}
