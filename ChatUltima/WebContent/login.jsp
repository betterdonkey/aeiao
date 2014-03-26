<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.5"/>
		
		<link type="text/css" href="resources/css/blitzer/jquery-ui-1.10.4.custom.min.css" rel="Stylesheet" />   
		<link rel="stylesheet" type="text/css" href="resources/css/chat.css" />
		
		<script type="text/javascript" src="resources/js/jquery-2.1.0.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery-ui-1.10.4.custom.min.js"></script>
		
		<script>
		

		function login(id, nick){
			$.ajax({		
				type: "put",
				contentType: "application/json",
				dataType: "json",
				url: "m/login",
				data: JSON.stringify({id: id,nick: nick}),
				error: function(){		
					console.log("Error logueando");
				},		
				success: function(data){	
					console.log("redirigiendo...");
					window.location.href = "index.jsp";
				}
			});
		}
		$(document).ready(function(){
			$.validator.addMethod("noSpace", function(value, element) { 
			    return value.indexOf(" ") < 0 && value != ""; 
			 });
			jQuery.validator.addMethod("alphanumeric", function(value, element) {
		        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		}); 
			$("#formulario").validate( {
				rules: {	
					nombre: {
						required: true,
						maxlength: 15,
						noSpace: true,
						alphanumeric: true
					}		
				},
				messages: {
					 nombre: {
						 noSpace: "No puede tener espacios",
						 required: "Introduzca el usuario",
						 maxlength: jQuery.format("Texto de {0} caracteres o menos"),
						 alphanumeric: "El texto tiene que ser alfanumerico"
					 }
				},
				submitHandler: function (form) {
						login(form.id.value,form.nombre.value); 
				}
			});
		});
		
		</script>
		<title>Ejemplo Chat</title>
	</head>
	
	<body>
	
	<h1>Chat</h1>
	<div>Introduzca un nombre de usuario :</div>
	<form id="formulario">
	
	<div>Nombre : <input type="text" name="nombre" id="nombre"/></div>
	<div>ID : <input type="text" name="id" id="id"/></div>
	<input type="submit">
	
	
	
	</form>
	
	</body>