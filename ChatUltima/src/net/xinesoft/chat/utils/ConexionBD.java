package net.xinesoft.chat.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private Connection connection;
	private String driver;
	private String db;
	private String usuario;
	private String password;

	public ConexionBD(String driver, String db, String usuario, String password) {
		this.driver = driver;
		this.db = db;
		this.usuario = usuario;
		this.password = password;
	}

	public void conectar() throws ClassNotFoundException, SQLException {
		Class.forName(this.driver);
		connection = DriverManager.getConnection(this.db, this.usuario, this.password);
	}

	public void desconectar() throws SQLException {
		connection.close();
	}

	public Connection getConnection() {
		return connection;
	}
}