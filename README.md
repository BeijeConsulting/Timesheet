# Beije MGMT Project

Api Controller
==============

In computer api controller:

-   getComputers: funziona ma non è usato nella jsp

-   getComputer: funziona ma non è usato nella jsp

-   insertComputer: testato ma non funziona il persist (400- The request
    sent by the client was syntactically incorrect.)

-   updateComputer: testato ma non funziona il persist, è da gestire il
    possibile null pointer quando si usa il metodo find(Computer.class,
    id)

Controller Computer
===================

In Controller Computer:

-   Per inserimento computer: insertcomputer.jsp-\>
    confirmdatacomputer(in controller)-\>confirmdatacomputer.jsp-\>
    registerdatacomputer(in controller)-\>registerdatacomputer.jsp
    (JPAService.save(computer)-\> conferma.jsp.\
    Funzionano tutti i metodi di controller e anche le pagine

-   Per cancellazione:

    -   metto un id che non esiste, mi fa uscire view informations con
        dati null, se fai conferma, error 400, sintattically non
        correct, problema persist.

    -   se metto un id esistente, non funziona la cancellazione
        (insertdisposaldate),

    Non cancella perchè chiama il metodo modify. Non esiste metodo
    delete

-   Per modificare:

    -   Se metto un id non esistente non funziona perchè non porta alla
        pagina idnontrovato.jsp

    -   Se metto id esistente, dovrebbe funzionare ma non si è riuscito
        a testare per via del collegamento non stabile al database
        remoto

    nota: quando si torna in home fa andare nella sezione utente

Molte funzionalità della pagina non sono state implementate, o chiamate.


# Gruppo Bravo (Address)

Api Controller
==============
AdressController.java è il controller non rest al cui interno troviamo 3 metodi: 
-   getAddresses che tramite URI riceve l'id dell'address da cercare e restituice il form useraddresses.jsp.
-   getForm che restituisce la jsp contenente il form per l'inserimento di un indirizzo. 
-   getAddresses che risponde alla richiesta "/registeraddresses" e restituisce la jsp confermaaddress con i dati inseriti in fase di registrazione passati tramite il model.

REST Controller
==============
AddressApiController.java è la classe del ControllerRest in cui al suo interno troviamo le API che si possono eseguire indipendentemente dalla login e che restituiscono in base alla chiamata inserita nell URI:
-   il primo metodo restituisce lalista degli indirizzi dell'utente specificato nell'id
-   il secondo inserisce un nuovo indirizzo associato ad uno specifico utente
-   il terzo metodo ritorna l'indirizzo salvato nel DB con l'id specificato tramite URL ilquarto ed ultimo metodo tramite la PUT va     ad aggiornare uno specifico indirizzo relativo all'id fornito nell URL.

AddressService.java è la classe nella quale si trovano i servizi messi a disposizione del nostro RestController in cui troviamo i metodi:
-   per creare un indirizzo relativo ad un utente
-   per modificare un determinato indirizzo
-   infine getAddressByUSer che ritorna la lista di indirizzi associati ad uno specifico utente, il cui id viene passato tramite url

AddressRepository.java è un interfaccia che dichiara un unico metodo a cui viene passato l'id dell'utente e ritorna tutti gli indirizzi associati all'utente.



# TIMESHEET - SEZIONE USER

	Entry-point: homepage.jsp
	
	LA PARTE USER PREVEDE, LATO ADMIN, OPERAZIONI CRUD SUL DB UTENTI



Lato server: CONTROLLER (MVC) --> it.beije.erp.timesheet.controller.UserController.java
===================
 
	EntryPoint:	@RequestMapping(value = "/home", method = RequestMethod.GET) --> home.jsp


##### Parte inserisci user		

home.jsp-->	@RequestMapping(value = "/insertuser", method = RequestMethod.GET) --> inserisciutente.jsp
			
		-->	 @RequestMapping(value = "/confirmdata", method = RequestMethod.POST) 
				
			--> confermadati.jsp
				
				--> @RequestMapping(value = "/conferma", method = RequestMethod.POST) 
					--> CONTROLLO SUI CAMPI 
						--> conferma.jsp (VISUALIZZA E TORNA A HOME)

##### Parte modifica user
				
home.jsp-->	@RequestMapping(value = "/modificautente", method = RequestMethod.GET) --> modificautente.jsp
		
			--> @RequestMapping(value = "/modificadati", method = RequestMethod.POST)   
																									
					/>--> IN BASE AL TRY CATCH SE PRENDE UN NULLPOINTER --> idnontrovato.jsp
				-->	
					\>-->	 IN BASE AL TRY CATCH SE NON RILANCIA EXC. --> modificadati.jsp con user nel model
								
					modificadati.jsp 
					--> @RequestMapping(value = "/confermamodificadati", method = RequestMethod.POST) 
						--> lancia il service per modificare l'user e torna 
							--> conferma.jsp (VISUALIZZA E TORNA A HOME)

##### Parte cerca e visualizza user
		
home.jsp-->	@RequestMapping(value = "/cercautente", method = RequestMethod.GET) --> cercautente.jsp	
			
			--> @RequestMapping(value = "/utentitrovati", method = RequestMethod.GET) 
					
					-->   CERCA UTENTI VIA SERVICE TRAMITE EMAIL/NOME/COGNOME/COD.FISC.
							DA PASSARE NEL MODEL A --> utentitrovati.jsp 
							
							CREA BOTTONE PER OGNI USER TROVATO
				
				--> @RequestMapping(value = "/visualizzautente", method = RequestMethod.POST) 
						
						--> APRE ENTITY MANAGER E FA UN FIND BY ID(@RequestParam) 
							DA PASSARE NEL MODEL A --> data.jsp CHE VISUALIZZA IL USER SCELTO

##### Parte cancella					
		
home.jsp-->	@RequestMapping(value = "/cancellautente", method = RequestMethod.POST) --> cancellautente.jsp

			--> @RequestMapping(value = "/confermacancellazione", method = RequestMethod.POST) 
						--> ARCHIVIA L'UTENTE VIA SERVICE E LANCIA --> confermacancellazione.jsp
																									
				--> VISUALIZZA TASTO PER TORNARE AD HOME 



Lato server: REST CONTROLLER --> it.beije.mgmt.restcontroller.UserApiController.java
===================			


##### GET USERS DTO 		

	--> @RequestMapping(value = "/users", method = RequestMethod.GET)
		public @ResponseBody List<UserDto> getUsers(Model model, HttpServletResponse response)
							
	
##### GET USER DTO	 	

	--> @RequestMapping(value = {"/user/{id}"}, method = RequestMethod.GET)
		public @ResponseBody UserDto getUserDto(@PathVariable Long id, 
															@RequestParam(required = false) boolean complete,
															Model model, HttpServletResponse response)

																			
##### GET USER 		

	--> @RequestMapping(value = {"/user_entity/{id}"}, method = RequestMethod.GET)
		public @ResponseBody User getUser(@PathVariable Long id, Model model,HttpServletResponse response)
			
			 		
##### INSERT USER 		

	--> @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody User insertUser(@RequestBody User user, HttpServletResponse response)	

					
##### UPDATE USER

	--> @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody User updateUser(@PathVariable Long id, @RequestBody User user, HttpServletResponse response)
		
//FIXARE QUESTA API (PROBLEMA DI LAZILY INITIALIZE SUGLI INDIRIZZI) !!!!!! N.B.
	
				
##### SEARCH USER DTO	
	
	--> @RequestMapping(value="/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<UserDto> searchUser(@RequestBody UserRequest req)

