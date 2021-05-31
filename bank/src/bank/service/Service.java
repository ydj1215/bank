package bank.service;

import bank.dao.BankDao;
import bank.vo.Account;

public class Service {
	private static Service service = new Service(); //본인의 클래스 변수를 하나 보유
	private Service() {}
	private BankDao dao = BankDao.getInstance();
	public static Service getInstance()
	{
		return service;
	}
	
	public void join(Account account) {
		// TODO Auto-generated method stub
		dao.join(account);
	}
	
	public boolean login(String id, String pwd) {
		// TODO Auto-generated method stub
		return dao.login(id, pwd);
	}

	public int deposit(String id, int money) {
		// TODO Auto-generated method stub
		return dao.deposit(id, money);
	}

	public int withdrawal(String id, int money) {
		// TODO Auto-generated method stub
		return dao.withdrawal(id,money);
	}

	public int query(String id) {
		// TODO Auto-generated method stub
		return dao.query(id);
	}
}
