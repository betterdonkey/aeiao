package net.xinesoft.chat.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import net.xinesoft.chat.utils.ConexionBD;
import net.xinesoft.chat.vo.*;


public class ChatDaoMysql implements IChatDao {
	
	private ConexionBD conexion;
	private ResourceBundle queries;
	
	public ChatDaoMysql(ConexionBD conexion) {
		this.conexion = conexion;
		this.queries = ResourceBundle.getBundle(ChatDaoMysql.class.getName());
	}
	
	@Override
	public void addChat(Chat chat) throws SQLException, ClassNotFoundException {

		this.conexion.conectar();
		
		PreparedStatement stmt = conexion.getConnection().prepareStatement(
				this.queries.getString("addChat"), PreparedStatement.RETURN_GENERATED_KEYS);
		
		stmt.setInt(1, chat.getUsuario().getId());
		stmt.setString(2, chat.getTexto());
		
		stmt.executeUpdate();		
		
		conexion.desconectar();
	}	
	/*
	@Override
	public void addChatPrivado(Usuario userA,Usuario userB, Chat chat) throws SQLException, ClassNotFoundException {

		this.conexion.conectar();
		
		PreparedStatement stmt = conexion.getConnection().prepareStatement(
				this.queries.getString("addChatPrivado"), PreparedStatement.RETURN_GENERATED_KEYS);
		String clave = userA.getNick()+userB.getNick();
		stmt.setString(1, clave);
		stmt.setString(2,chat.getTexto());
		
		stmt.executeUpdate();		
		
		conexion.desconectar();
	}
	*/
		
	@Override
	public List<Chat> getChats() throws ClassNotFoundException, SQLException {

		this.conexion.conectar();		

		PreparedStatement stmt = conexion.getConnection().prepareStatement(
				this.queries.getString("getChats"));
		
		ResultSet resultSetLogins = stmt.executeQuery();
		
		List<Chat> chats = null;
		
		while (resultSetLogins.next()){

			if(chats == null) chats = new LinkedList<Chat>();
			
			Chat chat = new Chat(resultSetLogins.getInt("id"),
					new Usuario(resultSetLogins.getInt("usuario_id"),resultSetLogins.getString("usuario_nick")),
					 resultSetLogins.getString("texto"));	
			chats.add(chat);
		 }
		
		 resultSetLogins.close(); 
		 this.conexion.desconectar();
		 return chats;
	}	
	
	@Override
	public void delChat() throws ClassNotFoundException, SQLException {

		this.conexion.conectar();		

		PreparedStatement stmt = conexion.getConnection().prepareStatement(
				this.queries.getString("delChat"),  PreparedStatement.RETURN_GENERATED_KEYS);
		
		stmt.executeUpdate();
		
		conexion.desconectar();
	}	
}