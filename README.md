# Timesheet Computer 
Timesheet Manager

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
