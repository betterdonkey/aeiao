package net.xinesoft.chat.manager;

import java.util.LinkedList;
import java.util.List;

import net.xinesoft.chat.vo.Usuario;

public class SessionManager {
	private static List<Usuario> usuariosActivos;
	
	private static boolean enSesion;
	
	static{
		enSesion = false;
		usuariosActivos = new LinkedList<Usuario>();
	}
	
	public synchronized void addSession(Usuario usuario){
		if(!usuariosActivos.contains(usuario)){
			setEnSesion(true);
			usuariosActivos.add(usuario);
		}
	}
	
	public synchronized static void delSession(Usuario usuario){
		if(usuariosActivos.contains(usuario)){
			setEnSesion(false);
			usuariosActivos.remove(usuario);
		}
	}
	
	public synchronized int getNumeroSessiones(){
		return usuariosActivos.size();
	}
	
	public synchronized  List<Usuario> getUsuariosSession(){
		return usuariosActivos;
	}

	public static boolean isEnSesion() {
		return enSesion;
	}

	public static void setEnSesion(boolean enSesion) {
		SessionManager.enSesion = enSesion;
	}
	
}
