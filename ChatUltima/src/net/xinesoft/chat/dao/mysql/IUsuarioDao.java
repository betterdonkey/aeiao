package net.xinesoft.chat.dao.mysql;

import java.sql.SQLException;
import java.util.List;

import net.xinesoft.chat.vo.Usuario;


public interface IUsuarioDao {
	public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException;
	public void addUsuario(Usuario usuario) throws SQLException,ClassNotFoundException;
	public void delUsuario(Usuario usuario) throws ClassNotFoundException,	SQLException;
}
