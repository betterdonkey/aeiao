package net.xinesoft.chat.dao.mysql;

import java.sql.SQLException;
import java.util.List;

import net.xinesoft.chat.vo.Chat;


public interface IChatDao {
	public List<Chat> getChats() throws ClassNotFoundException, SQLException;
	public void delChat() throws ClassNotFoundException, SQLException;
	public void addChat(Chat chat) throws SQLException,
			ClassNotFoundException;
}
