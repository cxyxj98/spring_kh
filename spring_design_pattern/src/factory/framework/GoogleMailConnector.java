package factory.framework;

import java.util.Date;

public class GoogleMailConnector extends SMTPConnector{

	protected GoogleMailConnector(String url, String id, String password, Date connectTime) {
		super(url, id, password, connectTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect() {
		System.out.println("gmail smtp 서버에 연결 성공했습니다.");
	}
}
