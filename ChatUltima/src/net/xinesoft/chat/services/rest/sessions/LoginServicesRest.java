package net.xinesoft.chat.services.rest.sessions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.xinesoft.chat.manager.SessionManager;
import net.xinesoft.chat.vo.Usuario;


@Path("/login")
public class LoginServicesRest {
	
	private static SessionManager sessionManager;
	
	static{
			sessionManager = new SessionManager();
	
	}
	@Context private HttpServletRequest request;	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public synchronized void iniciarSesion(Usuario usuario) throws ClassNotFoundException, SQLException{
		
		if(!sessionManager.getUsuariosSession().contains(usuario)){
			if(SessionManager.isEnSesion()){				
				SessionManager.delSession((Usuario)request.getSession().getAttribute("user"));
			}
			request.getSession().setAttribute("user",usuario);
			sessionManager.addSession(usuario);
			
			
		}		
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public synchronized void finalizarSesion() throws ClassNotFoundException, SQLException{

		if(request.getSession()!=null){
			SessionManager.delSession((Usuario)request.getSession().getAttribute("user"));
			request.getSession().removeAttribute("user");
		}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized Usuario obtenerSesion() throws ClassNotFoundException, SQLException{
		return (Usuario) request.getSession().getAttribute("user");
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized List<Usuario> obtenerUsuariosSesion() throws ClassNotFoundException, SQLException{
		return sessionManager.getUsuariosSession();
	}

}