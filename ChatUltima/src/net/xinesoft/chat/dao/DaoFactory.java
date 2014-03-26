package net.xinesoft.chat.dao;

import java.sql.SQLException;

import net.xinesoft.chat.dao.mysql.IChatDao;
import net.xinesoft.chat.dao.mysql.IUsuarioDao;

public abstract class DaoFactory {
	
	public final static int MYSQL = 1;
	public final static int MYSQL_TEST = 2;
	
	public static DaoFactory getDaoFactory(int sgbd){
		switch(sgbd){
			case MYSQL:
				return new MysqlDaoFactory();
			case MYSQL_TEST:
				return new MysqlDaoFactory(true);
			default:
				return null;
		}
	}	
	
	public abstract IChatDao getChatDao() throws ClassNotFoundException, SQLException;
	public abstract IUsuarioDao getUsuarioDao() throws ClassNotFoundException, SQLException;
}