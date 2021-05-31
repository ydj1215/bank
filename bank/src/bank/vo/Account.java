package bank.vo;

public class Account {
	private String id;
	private String pwd;
	private int money;
	
	public Account() {} //↓에 오버로딩을 했기 때문에 하나 넣어주자
	
	public Account(String id, String pwd)
	{
		this.id=id;
		this.pwd=pwd;
		this.money = 0;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
}
