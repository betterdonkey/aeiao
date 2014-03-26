package net.xinesoft.chat.manager;

import java.sql.SQLException;
import java.util.List;

import net.xinesoft.chat.dao.DaoFactory;
import net.xinesoft.chat.dao.mysql.IUsuarioDao;
import net.xinesoft.chat.vo.Usuario;

public class UsuarioManager {
	
	private IUsuarioDao usuarioDao;
	
	public UsuarioManager() throws ClassNotFoundException, SQLException {
		this.usuarioDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getUsuarioDao();
	}

	public synchronized List<Usuario> getUsuarios() throws SQLException,	ClassNotFoundException {
		return this.usuarioDao.getUsuarios();
	}

	public synchronized void addUsuario(Usuario usuario) throws SQLException,ClassNotFoundException {
		this.usuarioDao.addUsuario(usuario);
	}
	
	public synchronized void delUsuario(Usuario usuario) throws SQLException,ClassNotFoundException {
		this.usuarioDao.delUsuario(usuario);
	}
	
}