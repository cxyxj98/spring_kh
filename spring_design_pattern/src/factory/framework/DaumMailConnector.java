package factory.framework;

import java.util.Date;

public class DaumMailConnector extends SMTPConnector{

	protected DaumMailConnector(String url, String id, String password, Date connectTime) {
		super(url, id, password, connectTime);
	}

	@Override
	public void connect() {
		System.out.println("다음 SMTP 서버와 연결에 성공했습니다.");
	}
}
