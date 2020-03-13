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
