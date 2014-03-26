package net.xinesoft.chat.vo;

public class Usuario {
	private int id;
	private String nick;
	private String pass;
	
	public Usuario() {}
	
	public Usuario(int id, String nick,String pass) {
		this.id = id;
		this.nick = nick;
		this.pass = pass;
	}
	public Usuario(String nick,String pass) {
		this.nick = nick;
		this.pass = pass;
	}
	public Usuario(int id, String nick) {
		this.id = id;
		this.nick = nick;
	}
	public Usuario(String nick) {
		this.nick = nick;
	}	
	public Usuario(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
