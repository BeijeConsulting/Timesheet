--------------------------------TIMESHEET SEZIONE USER-------------------------------------------------
Entry-point: homepage.jsp

LA PARTE USER PREVEDE, LATO ADMIN, OPERAZIONI CRUD SUL DB UTENTI

_________________________________________________________________________________________________________________________________________________________________________
LATO SERVER:

- CONTROLLER (MVC) --> it.beije.erp.timesheet.controller.UserController.java
	
	EntryPoint:	@RequestMapping(value = "/home", method = RequestMethod.GET) --> home.jsp
	
------------------------------------------------------------PARTE INSERISCI-----------------------------------------------------------------------------------------------			

home.jsp-->	@RequestMapping(value = "/insertuser", method = RequestMethod.GET) --> inserisciutente.jsp
			
			-->	@RequestMapping(value = "/confirmdata", method = RequestMethod.POST) --> confermadati.jsp
				
				--> @RequestMapping(value = "/conferma", method = RequestMethod.POST) --> CONTROLLO SUI CAMPI --> conferma.jsp (VISUALIZZA E TORNA A HOME)
			
------------------------------------------------------------PARTE MODIFICA------------------------------------------------------------------------------------------------			
home.jsp-->	@RequestMapping(value = "/modificautente", method = RequestMethod.GET) --> modificautente.jsp
		
			--> @RequestMapping(value = "/modificadati", method = RequestMethod.POST)   --> IN BASE AL TRY CATCH SE PRENDE UN NULLPOINTER --> idnontrovato.jsp
																						-->	IN BASE AL TRY CATCH SE NON RILANCIA EXC. 
																								--> modificadati.jsp con user nel model
				
				modificadati.jsp --> @RequestMapping(value = "/confermamodificadati", method = RequestMethod.POST) 
					--> lancia il service per modificare l'user e torna conferma.jsp (VISUALIZZA E TORNA A HOME)
					
------------------------------------------------------------PARTE CERCA E VISUALIZZA--------------------------------------------------------------------------------------			
		
home.jsp-->	@RequestMapping(value = "/cercautente", method = RequestMethod.GET) --> cercautente.jsp	
			
			--> @RequestMapping(value = "/utentitrovati", method = RequestMethod.GET) --> 	CERCA UTENTI VIA SERVICE TRAMITE EMAIL/NOME/COGNOME/COD.FISC.
																							DA PASSARE NEL MODEL A --> utentitrovati.jsp CREA BOTTONE PER 
																							OGNI USER TROVATO
				
				--> @RequestMapping(value = "/visualizzautente", method = RequestMethod.POST) --> 	APRE ENTITY MANAGER E FA UN FIND BY ID(@RequestParam) 
																									DA PASSARE NEL MODEL A --> data.jsp CHE VISUALIZZA 
																									IL USER SCELTO
																									
------------------------------------------------------------PARTE CANCELLA------------------------------------------------------------------------------------------------			
		
home.jsp-->	@RequestMapping(value = "/cancellautente", method = RequestMethod.POST) --> cancellautente.jsp

			--> @RequestMapping(value = "/confermacancellazione", method = RequestMethod.POST) --> 	ARCHIVIA L'UTENTE VIA SERVICE E LANCIA 
																										--> confermacancellazione.jsp
																									
				--> VISUALIZZA TASTO PER TORNARE AD HOME 
				
		
------------------------------------------------------------PARTE API REST CONTROLLER-------------------------------------------------------------------------------------			

- REST CONTROLLER --> it.beije.mgmt.restcontroller.UserApiController.java

	GET USERS DTO 		--> @RequestMapping(value = "/users", method = RequestMethod.GET)
							public @ResponseBody List<UserDto> getUsers(Model model, HttpServletResponse response)
	
	GET USER DTO	 	--> @RequestMapping(value = {"/user/{id}"}, method = RequestMethod.GET)
							public @ResponseBody UserDto getUserDto(@PathVariable Long id, @RequestParam(required = false) boolean complete, Model model, 
														HttpServletResponse response)
														
	GET USER 			--> @RequestMapping(value = {"/user_entity/{id}"}, method = RequestMethod.GET)
							public @ResponseBody User getUser(@PathVariable Long id, Model model,HttpServletResponse response) 		

	INSERT USER 		--> @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
							public @ResponseBody User insertUser(@RequestBody User user, HttpServletResponse response)	

	UPDATE USER 		--> //FIXARE QUESTA API (PROBLEMA DI LAZILY INITIALIZE SUGLI INDIRIZZI) !!!!!! N.B.
							@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
							public @ResponseBody User updateUser(@PathVariable Long id, @RequestBody User user, HttpServletResponse response)
							

	SEARCH USER DTO		--> @RequestMapping(value="/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
							public @ResponseBody List<UserDto> searchUser(@RequestBody UserRequest req)


_________________________________________________________________________________________________________________________________________________________________________	
LATO CLIENT: CHIAMATE REST VIA SCRIPT AJAX


