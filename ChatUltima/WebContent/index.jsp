<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.5"/>
		<link type="text/css" href="resources/css/blitzer/jquery-ui-1.10.4.custom.min.css" rel="Stylesheet" />     
		<link rel="stylesheet" type="text/css" href="resources/css/chat.css" />
		<script type="text/javascript" src="resources/js/jquery-2.1.0.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="resources/js/chat.js"></script>		 
		<script type="text/javascript" src="resources/js/jquery-ui-1.10.4.custom.min.js"></script>
		
		<title>Ejemplo Chat</title>
	</head>
	<body>	
    
    
		
		<div id="contenedor">
									
			
			
			
			<div id="usuarios">
				<div id="numusuarios"></div>
				<div id="listausuarios"></div>
			</div>
			<div class="cubrechat">
			<h1>Chat</h1>
			<div id="logout"></div>
			<div id="panel"><div id="info"><p><i>"@clear"</i> para borrar el chat</p></div></div>
			<div id="chat"></div>
			<form id="formulario">	
				<div class="bloque">
					<div>Texto :</div>
					<div><input type="text" name="texto"/> <input type="submit"/></div>
				</div>
				<div class="centro"><label for="texto" class="error"></label></div>		
				
			</form>
			</div>
		</div>
		
	</body>
</html>