package net.xinesoft.chat.manager;

import java.sql.SQLException;
import java.util.List;


import net.xinesoft.chat.dao.DaoFactory;
import net.xinesoft.chat.dao.mysql.IChatDao;
import net.xinesoft.chat.vo.Chat;


public class ChatManager {
	
	private IChatDao chatDao;
	
	public ChatManager() throws ClassNotFoundException, SQLException {
		this.chatDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getChatDao();
	}

	public synchronized List<Chat> getChats() throws SQLException,	ClassNotFoundException {		
		return this.chatDao.getChats();
	}

	public synchronized void addChat(Chat chat ) throws SQLException,ClassNotFoundException {		
		this.chatDao.addChat(chat);
	}
	public synchronized void delChat() throws SQLException,ClassNotFoundException {
		this.chatDao.delChat();
	}
}