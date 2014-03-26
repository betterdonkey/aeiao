package net.xinesoft.chat.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.xinesoft.chat.manager.SessionManager;
import net.xinesoft.chat.vo.Usuario;

public class SessionCounter implements HttpSessionListener {
	public static Integer usuarioConectados = 0;
	
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Session creada");
		arg0.getSession().setMaxInactiveInterval(10);
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
	
		if(arg0.getSession().getAttribute("user") != null){
			Usuario usuario = (Usuario) arg0.getSession().getAttribute("user");
			System.out.println("Session destruida :"+usuario.getNick());
			SessionManager.delSession(usuario);
			arg0.getSession().removeAttribute("user");
		}else{
			System.out.println("Session destruida : Anonimo");
		}
	}
}