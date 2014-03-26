package net.xinesoft.chat.dao;
import java.sql.SQLException;
import java.util.ResourceBundle;

import net.xinesoft.chat.dao.mysql.ChatDaoMysql;
import net.xinesoft.chat.dao.mysql.IChatDao;
import net.xinesoft.chat.dao.mysql.IUsuarioDao;
import net.xinesoft.chat.dao.mysql.UsuarioDaoMysql;
import net.xinesoft.chat.utils.ConexionBD;


public class MysqlDaoFactory extends DaoFactory {
	
	private ResourceBundle queries = ResourceBundle.getBundle(MysqlDaoFactory.class.getName());	
	boolean pruebas;
	
	public MysqlDaoFactory() {
			pruebas = false;		
	}
	public MysqlDaoFactory(boolean test) {
			pruebas = test;	
	}
	
	private ConexionBD createConnection() throws ClassNotFoundException, SQLException {
		return new ConexionBD(this.queries.getString("driver"),
							  pruebas ? this.queries.getString("bd_test") : this.queries.getString("bd"), 
							  this.queries.getString("usuario"),
							  this.queries.getString("password"));
	}
	
	@Override
	public IChatDao getChatDao() throws ClassNotFoundException,SQLException {
		return new ChatDaoMysql(createConnection());
	}
	
	@Override
	public IUsuarioDao getUsuarioDao() throws ClassNotFoundException,SQLException {
		return new UsuarioDaoMysql(createConnection());
	}
}