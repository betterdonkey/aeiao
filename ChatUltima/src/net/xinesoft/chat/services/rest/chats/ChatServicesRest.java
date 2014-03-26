package net.xinesoft.chat.services.rest.chats;

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

import net.xinesoft.chat.manager.ChatManager;
import net.xinesoft.chat.vo.Chat;
import net.xinesoft.chat.vo.Usuario;


@Path("/chats")
public class ChatServicesRest {	
	private static ChatManager chatManager;
	@Context private HttpServletRequest request;	
	static{
			try {
				chatManager = new ChatManager();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}	
	}	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized String getChats() throws ClassNotFoundException, SQLException{		
		List<Chat> chats = chatManager.getChats();
		String json = "[";
		for(Chat chat : chats){
			json += "{\"nick\":\""+chat.getUsuario().getNick()+"\",\"texto\":\""+chat.getTexto()+"\"},";
		}
		json = json.substring(0, json.length() - 1);
		json += "]";
		return json;
	}	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public synchronized void  createMensaje(Chat chat) throws ClassNotFoundException, SQLException{		
		if(request.getSession().getAttribute("user") != null){
			Usuario usuario = (Usuario)request.getSession().getAttribute("user");
			chat.setUsuario(usuario);
			chatManager.addChat(chat);
		}
	}	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public synchronized void delChat() throws ClassNotFoundException, SQLException{
		chatManager.delChat();
	}
}