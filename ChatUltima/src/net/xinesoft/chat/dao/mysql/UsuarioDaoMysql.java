package net.xinesoft.chat.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import net.xinesoft.chat.utils.ConexionBD;
import net.xinesoft.chat.vo.*;


public class UsuarioDaoMysql implements IUsuarioDao {
	
	private ConexionBD conexion;
	private ResourceBundle queries;
	
	public UsuarioDaoMysql(ConexionBD conexion) {
		this.conexion = conexion;
		this.queries = ResourceBundle.getBundle(UsuarioDaoMysql.class.getName());
	}
	
	@Override
	public void addUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {

		this.conexion.conectar();
		
		PreparedStatement stmt = conexion.getConnection().prepareStatement(
				this.queries.getString("addUsuario"), PreparedStatement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, usuario.getNick());
		stmt.setString(2, usuario.getPass());
		
		stmt.executeUpdate();		
		
		conexion.desconectar();
	}
		
	@Override
	public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException {

		this.conexion.conectar();		

		PreparedStatement stmt = conexion.getConnection().prepareStatement(
				this.queries.getString("getUsuarios"));
		
		ResultSet resultSetLogins = stmt.executeQuery();
		
		List<Usuario> chats = null;
		
		while (resultSetLogins.next()){

			if(chats == null) chats = new LinkedList<Usuario>();
			
			Usuario chat = new Usuario(
					 resultSetLogins.getInt("id"),
					 resultSetLogins.getString("nick"),
					 resultSetLogins.getString("pass"));	
			chats.add(chat);
		 }
		
		 resultSetLogins.close(); 
		 this.conexion.desconectar();
		 return chats;
	}	

	@Override
	public void delUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {

		this.conexion.conectar();		

		PreparedStatement stmt = conexion.getConnection().prepareStatement(
				this.queries.getString("delUsuario"),  PreparedStatement.RETURN_GENERATED_KEYS);
		
		stmt.setInt(1, usuario.getId());
		
		stmt.executeUpdate();		
		
		conexion.desconectar();
	}
}