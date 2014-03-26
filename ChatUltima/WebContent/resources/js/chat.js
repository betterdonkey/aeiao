var mensajes = new Array();
var usuarios = new Array();

function obtenerChat(){	
	$.ajax({		
		type: "get",
		dataType: "json",
		url: "m/chats",
		error: function(){		
			console.log("Error obteniendo mensajes");
		},		
		success: function(data){
			
			if(data == undefined){
				if($("#nomensaje").size() < 1){
					$("#nick").hide();
					$("#chat").empty();
					$("#chat").append("<div id=\"nomensaje\"><h2>No hay mensajes</h2></div>");
					mensajes = new Array();
				}
			}else if(mensajes.length < data.length){
					$("#nick").show();
					$("#chat").empty();
					mensajes = data;
					mensajes.forEach(function(item,index){							
						item.texto = ponerIconos(item.texto);		
						
						item.nick = ponerIconos(item.nick);
						$("#chat").append("<div class=\"mensaje\">"+
								"<div class=\"nombre\">" + item.nick + "</div>"+
								"<div class=\"texto\">" + item.texto +"</div>"+
								"</div>");
					});
					if ($("#chat")[0].scrollHeight >  $("#chat").height()) 
						$(".mensaje").last().css("border-bottom", "none");		
						
					hacerScroll();
			}				
		}
	});
	setTimeout(obtenerChat, 500);	
}

function insertarChat(texto){
	$.ajax({		
		type: "put",
		contentType: "application/json",
		dataType: "json",
		url: "m/chats",
		data: JSON.stringify({texto: texto}),
		error: function(){		
			console.log("Error enviando mensaje");
		},		
		success: function(data){			
			$("input[name=texto]").val("");
			obtenerChat();
		}
	});
}

function borrarChat(){
	$.ajax({		
		type: "delete",
		contentType: "application/json",
		url: "m/chats",		
		success: function(){
			$("#formulario")[0].reset();
			obtenerChat();	
		}	
	});
}

function validarFormulario(){	
	
	$("#formulario").validate( {
		rules: {	
			texto: {
				maxlength: 200
			},			
		},
		messages: {
			 texto: {
				 maxlength: jQuery.format("Texto de {0} caracteres o menos")
			 }
		},
		submitHandler: function (form) {
			if($("input[name=texto]").val() == "@clear")
				borrarChat();
			else{
				if($("input[name=texto]").val().trim() != ""){
					insertarChat(form.texto.value); 
				}
			}
				
		}
	});
}

function hacerScroll(){
	$("#chat")[0].scrollTop = $("#chat")[0].scrollHeight;
}

function ponerIconos(texto){
	if(texto.indexOf(">") > 0 )
		texto =  "<img src=\"resources/img/bad.gif\">";
	if(texto.indexOf("<") > 0 )
		texto =  "<img src=\"resources/img/bad.gif\">";
	
	texto = texto.replace(/\:\)/g, "<img src=\"resources/img/smiley.png\">");
	texto = texto.replace(/\:\(/g, "<img src=\"resources/img/sad.png\">");
	texto = texto.replace(/\:\@/g, "<img src=\"resources/img/bad.gif\">");
	return texto;
}
function logout(){
	$.ajax({		
		type: "delete",
		url: "m/login",
		error: function(){		
			console.log("Error deslogueando");
		},
		success: function(){
			location.reload();
			window.location.href = "login.jsp";
		}
	});
}
function getNumeroUsuarios(){	
	$.ajax({		
		type: "get",
		url: "m/login/all",
		dataType: "json",
		error: function(){		
			console.log("Error mostrando numero usuarios");
		},
		success: function(data){
			if(usuarios.length < data.length || usuarios.length > data.length){			
				$("#numusuarios").empty();
				$("#listausuarios").empty();
				$("#numusuarios").append("<div><b>Lista de usuarios : </b>"+data.length+"</div>");
				data.forEach(function(item,index){	
					$("#listausuarios").append("<div>"+item.nick+"</div>");
				});
				$("#listausuarios").effect( "shake",{times:4,distance:10,direction:"left"}, 800 );
			}
			usuarios = data;
		}
	});
	setTimeout(getNumeroUsuarios, 500);	
}
function getUser(){
	$.ajax({		
		type: "get",
		url: "m/login",
		dataType: "json",
		error: function(){		
			console.log("Error mostrando usuario");
		},
		success: function(data){
			if(data == null){
				location.reload();
			}else{
				$("#logout").empty();
				$("#logout").append("Logout - "+data.nick);
			}
			
		}
	});
	setTimeout(getUser, 2000);	
}

$(document).ready(function(){
	obtenerChat();
	getNumeroUsuarios();
	getUser();
	validarFormulario();
	$("#logout").on("click",function(){
		logout();
	});
});