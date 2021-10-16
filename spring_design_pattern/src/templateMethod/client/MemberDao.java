package templateMethod.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import templateMethod.framework.dao.AbstractMemberDao;

public class MemberDao extends AbstractMemberDao{

	@Override
	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bm", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

}
