package net.xinesoft.chat.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Chat {
	private int id;
	private Usuario usuario;
	private String texto;
	
	public Chat(){
		
	}
	public Chat( Usuario usuario, String texto){
		this.usuario = usuario;
		this.texto = texto;
	}
	public Chat(int id, Usuario usuario, String texto){
		this.id = id;
		this.usuario = usuario;
		this.texto = texto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
