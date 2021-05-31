package bank.vo;

public class Account {
	private String id;
	private String pwd;
	private int money;
	
	public Account() {} //↓에 오버로딩을 했기 때문에 하나 넣어주자
	
	public Account(String tid, String tPwd)
	{
		this.id=tid;
		this.pwd=tPwd;
		this.money = 0;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String tId) {
		this.id = tId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String tPwd) {
		this.pwd = tPwd;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int tMoney) {
		this.money = tMoney;
	}
}
