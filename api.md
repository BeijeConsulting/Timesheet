## Address

### Elenco indirizzi [/addresses/user/{id} GET]
Ritorna l'elenco degli indirizzi di un determinato User

+ Parameters
    + id(long) id dell'user

+ Response 200
    ```json
    [
        {
            "id": 40,
            "idUser": 2,
            "street": "via rossi",
            "city": "Lissone",
            "province": "MB",
            "cap": "20865",
            "country": "IT",
            "startDate": "2019-11-15",
            "endDate": null,
            "type": "R"
        },
        {
            "id": 41,
            "idUser": 2,
            "street": "via rossi",
            "city": "Lissone",
            "province": "MB",
            "cap": "20865",
            "country": "IT",
            "startDate": "2019-11-15",
            "endDate": null,
            "type": "R"
        },
        ...
    ]
    ```
+ Response 222
    ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato un utente con l'id selezionato"
    }
    ```
### Aggiunta nuovo indirizzo [/address/user/{id} POST]
Aggiunta di un nuovo indirizzo dato un id user

+ Parameters
  + id(long) id dell'user

+ Request
    ```json
    {
        "street": "via rossi",
        "city": "Lissone",
        "province": "MB",
        "cap": "20865",
        "country": "IT",
        "startDate": "2019-11-15",
        "endDate": null,
        "type": "R"
    }
    ```
+ Response 201
    ```json
    {
        "id": 41,
        "idUser": 2,
        "street": "via rossi",
        "city": "Lissone",
        "province": "MB",
        "cap": "20865",
        "country": "IT",
        "startDate": "2019-11-15",
        "endDate": null,
        "type": "R"
    }
    ```
+ Response 222
    ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato un utente con l'id selezionato"
    }
    ```
+ Response 223
    ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "223 error in JSON"
        "message": "Non è stato possibile aggiungere l'indirizzo desiderato"
    }
    ```

### Dettagli Address [/address/{id} GET]
Visualizza i dettagli di un indirizzo dato il suo id
+ Parameters
  + id(long) id dell'indirizzo
  
 + Response 200
    ```json
    {
            "id": 41,
            "idUser": 2,
            "street": "via rossi",
            "city": "Lissone",
            "province": "MB",
            "cap": "20865",
            "country": "IT",
            "startDate": "2019-11-15",
            "endDate": null,
            "type": "R"
    }
     ```
  + Response 222
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato un indirizzo con l'id selezionato"
    }
    ```
    
### Modifica dell'indirizzo [/address/{id} PUT]

+ Parameters
  + id(long) id dell'indirizzo

+ Request
    ```json
    {
        "street": "via rossi",
        "city": "Lissone",
        "province": "MB",
        "cap": "20865",
        "country": "IT",
        "startDate": "2019-11-15",
        "endDate": null,
        "type": "R"
    }
    ```

+ Response 200
    ```json
    {
        "id": 41,
        "idUser": 2,
        "street": "via rossi",
        "city": "Lissone",
        "province": "MB",
        "cap": "20865",
        "country": "IT",
        "startDate": "2019-11-15",
        "endDate": null,
        "type": "R"
    }
    ```
+ Response 222
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato un indirizzo con l'id selezionato"
    }
    ```
+ Response 223
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "223 error in JSON"
        "message": "Non è stato possibile modificare i dati dell'indirizzo desiderato"
    }
    ```



## Bank Credential

### Storico bankCredentials [/bank_credentials/user/{id} GET]
Ritorna l'elenco delle Bank Credentials di un determinato User

+ Parameters
  + id(long) id dell'user

+ Response 200
    ```json
   [
        {
            "id": 1,
            "idUser": 1,
            "accountholder": "Ivo7",
            "iban": "IBAN45",
            "swift": "swift12",
            "startDate": "2019-11-12",
            "endDate": "2019-11-13",
            "notes": "TEST",
            "assignment": null
        },
        {
            "id": 2,
            "idUser": 1,
            "accountholder": "Ivo2",
            "iban": "IBAN2",
            "swift": "swift2",
            "startDate": "2019-11-12",
            "endDate": "2019-12-20",
            "notes": "TEST2",
            "assignment": null
        },
        ...
    ]
    ```
+ Response 222
    ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato un utente con l'id selezionato"
    }
    ```
### Creazione nuovo BankCredential [/bank_credentials/user/{id} POST]
Creazione nuovo BankCredentials a partire da un User

+ Parameters
  + id(long) id user

+ Request
    ```json
    {
        "accountholder": "Ivo7",
        "iban": "IBAN45",
        "swift": "swift12",
        "startDate": "2019-11-12",
        "endDate": "2019-11-13",
        "notes": "TEST",
        "assignment": null
    }
    ```
+ Response 201
    ```json
    {
        "id": 1,
        "idUser": 1,
        "accountholder": "Ivo7",
        "iban": "IBAN45",
        "swift": "swift12",
        "startDate": "2019-11-12",
        "endDate": "2019-11-13",
        "notes": "TEST",
        "assignment": null
    }
    ```
+ Response 222
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato un utente con l'id selezionato"
    }
    ```
+ Response 223
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "223 error in JSON"
        "message": "Non è stato possibile aggiungere la cordinata bancaria desiderata"
    }
    ```

### Dettagli Bank Credentials [/bank_credentials/{id} GET]
+ Parameters
  + id(long) id Bank Credentials
  
 + Response 200
    ```json
    {
         "id": 1,
        "idUser": 1,
        "accountholder": "Ivo7",
        "iban": "IBAN45",
        "swift": "swift12",
        "startDate": "2019-11-12",
        "endDate": "2019-11-13",
        "notes": "TEST",
        "assignment": null
    }
    ```
  + Response 222
    ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato una cordinata bancaria con l'id selezionato"
    }
    ```
    
### Modifica Bank Credentials [/bank_credentials/{id} PUT]

+ Parameters
  + id(long) id Bank Credentials

+ Request
    ```json
    {
        "accountholder": "Ivo7",
        "iban": "IBAN45",
        "swift": "swift12",
        "startDate": "2019-11-12",
        "endDate": "2019-11-13",
        "notes": "TEST",
        "assignment": null
    }
    ```

+ Response 200
    ```json
    {
        "id": 1,
        "idUser": 1,
        "accountholder": "Ivo7",
        "iban": "IBAN45",
        "swift": "swift12",
        "startDate": "2019-11-12",
        "endDate": "2019-11-13",
        "notes": "TEST",
        "assignment": null
    }
    ```
+ Response 222
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 invalid id",
        "message": "Non è stato trovato una cordinata bancaria con l'id selezionato"
    }
    ```
+ Response 223
    ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "223 error in JSON"
        "message": "Non è stato possibile modificare i dati della cordinata bancaria desiderata"
    }
    ```
## User

### Elenco users [/users GET]
Ritorna l'elenco degli utenti presenti

+ Response 200
    ```json
    [
        {
            "id": ,
            "firstName": "",
            "lastName": "",
            "email": "",
            "phone": ""
        },
        {
            "id": ,
            "firstName": "",
            "lastName": "",
            "email": "",
            "phone": ""
        },
        ...
    ]
    ```

### Dettaglio user [/user/{id} GET]

Ritorna le informazioni di un user.

+ Parameters
    + id(long) id dell'user,
    + complete(boolean) se true richiede informazioni piÃ¹ dettagliate

+ Response 200
    + complete == false
        ```json
        {
            "id": 31,
            "firstName": "",
            "lastName": "",
            "email": "",
            "phone": ""
        }
        ```
    + complete == true
        ```json
        {
            "id": 31,
            "firstName": "",
            "lastName": "",
            "email": "",
            "addresses": [],
            "bankCredential": {
                "id": 12,
                "idUser": 31,
                "accountholder": "account2",
                "iban": "",
                "swift": null,
                "startDate": "2010-02-05",
                "endDate": null,
                "notes": null,
                "assignment": null
            },
            "contract": {
                "id": 7,
                "idUser": 31,
                "ccnl": null,
                "lvl": 3,
                "minimoContrattuale": 3.0,
                "superminimo": 0.0,
                "retribuzioneMensile": 0.0,
                "ral": 0.0,
                "nettoMensile": 0.0,
                "costoInterno": 5.0,
                "note": null,
                "startDate": "2002-05-01",
                "endDate": null,
                "contract_type": "2"
            }
        }
        ```
+ Response 222
     ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 Invalid Id",
        "message": "Non Ã¨ stato trovato un utente con l'id selezionato"
    }
     ```
### Storico user [/user_entity/{id} GET]

Ritorna le informazioni di un user comprensivo di tutti gli storici.

+ Parameters
    + id(long) id dell'user

+ Response 200
    ```json
    {
        "id": 31,
        "firstName": "Mario",
        "lastName": "Merio",
        "email": "mario@merio.it",
        "password": "0000",
        "phone": null,
        "secondaryEmail": null,
        "fiscalCode": null,
        "birthDate": null,
        "birthPlace": null,
        "nationality": null,
        "document": null,
        "idSkype": null,
        "admin": null,
        "archiveDate": null,
        "note": null,
        "picUrl": null,
        "addresses": [],
        "bank_credentials": [
            {
                "id": 10,
                "idUser": 31,
                "accountholder": "account",
                "iban": "AAA",
                "swift": null,
                "startDate": "2010-02-05",
                "endDate": "2020-03-13",
                "notes": null,
                "assignment": null
            },
            {
                "id": 11,
                "idUser": 31,
                "accountholder": "account",
                "iban": "BBB",
                "swift": null,
                "startDate": "2010-02-05",
                "endDate": "2020-03-13",
                "notes": null,
                "assignment": null
            },
            {
                "id": 12,
                "idUser": 31,
                "accountholder": "account2",
                "iban": "CCC",
                "swift": null,
                "startDate": "2010-02-05",
                "endDate": null,
                "notes": null,
                "assignment": null
            }
        ],
        "contracts": [
            {
                "id": 6,
                "idUser": 31,
                "ccnl": null,
                "lvl": 2,
                "minimoContrattuale": 3.0,
                "superminimo": null,
                "retribuzioneMensile": null,
                "ral": null,
                "nettoMensile": null,
                "costoInterno": 5.0,
                "note": null,
                "startDate": "2002-05-01",
                "endDate": "2020-03-12",
                "contract_type": "2"
            },
            {
                "id": 7,
                "idUser": 31,
                "ccnl": null,
                "lvl": 3,
                "minimoContrattuale": 3.0,
                "superminimo": 0.0,
                "retribuzioneMensile": 0.0,
                "ral": 0.0,
                "nettoMensile": 0.0,
                "costoInterno": 5.0,
                "note": null,
                "startDate": "2002-05-01",
                "endDate": null,
                "contract_type": "2"
            }
        ]
    }
    ```
+ Response 222
     ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 Invalid Id",
        "message": "Non Ã¨ stato trovato un utente con l'id selezionato"
    }
     ```
    
### Creazione nuovo user [/user POST]
Creazione nuovo user.

+ Request
    ```json
    {
        "firstName": "Mario",
        "lastName": "Merio",
        "email": "mario@merio.it",
        "password": "0000",
        "phone": null,
        "secondaryEmail": null,
        "fiscalCode": null,
        "birthDate": null,
        "birthPlace": null,
        "nationality": null,
        "document": null,
        "idSkype": null,
        "admin": null,
        "archiveDate": null,
        "note": null,
        "picUrl": null,
        "addresses": [],
        "bank_credentials": [],
        "contracts": []
    }
    ```
+ Response 201
    ```json
    {
        "id": 31,
        "firstName": "Mario",
        "lastName": "Merio",
        "email": "mario@merio.it",
        "password": "0000",
        "phone": null,
        "secondaryEmail": null,
        "fiscalCode": null,
        "birthDate": null,
        "birthPlace": null,
        "nationality": null,
        "document": null,
        "idSkype": null,
        "admin": null,
        "archiveDate": null,
        "note": null,
        "picUrl": null,
        "addresses": [],
        "bank_credentials": [],
        "contracts": []
    }
    ```
+ Response 223
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "223 Error in user",
        "message": "Non Ã¨ stato possibile aggiungere l'utente desiderato"
    }
    ```
    
### Modifica dati user [/user/{id} PUT]

+ Parameters
    + id(long) id dell'user da modificare

+ Request
    ```json
    {
        "firstName": "Mario",
        "lastName": "Merio",
        "email": "mario@merio.it",
        "password": "0000",
        "phone": null,
        "secondaryEmail": null,
        "fiscalCode": null,
        "birthDate": null,
        "birthPlace": null,
        "nationality": null,
        "document": null,
        "idSkype": null,
        "admin": null,
        "archiveDate": null,
        "note": null,
        "picUrl": null,
        "addresses": [],
        "bank_credentials": [],
        "contracts": []
    }
    ```
+ Response 200
    ```json
    {
        "firstName": "Mario",
        "lastName": "Merio",
        "email": "mario@merio.it",
        "password": "0000",
        "phone": null,
        "secondaryEmail": null,
        "fiscalCode": null,
        "birthDate": null,
        "birthPlace": null,
        "nationality": null,
        "document": null,
        "idSkype": null,
        "admin": null,
        "archiveDate": null,
        "note": null,
        "picUrl": null,
        "addresses": [],
        "bank_credentials": [],
        "contracts": []
    }
    ```
+ Response 222
     ```json
    {
        "time": "2020-04-02T12:54:35.924",
        "status": "222 Invalid Id",
        "message": "Non Ã¨ stato trovato un utente con l'id selezionato"
    }
     ```
+ Response 223
    ```json
     {
        "time": "2020-04-02T12:54:35.924",
        "status": "223 Error in user",
        "message": "Non Ã¨ stato possibile modificare i dati dell'utente desiderato"
    }
    ```

## Ricerca occorrenze presenti in user data una Stringa [/search POST]

+ Request
    ```json
    {
        "firstName": "Mario",
        "lastName": "Merio",
        "email": "mario@merio.it",
        "fiscalCode": null
    }
    ```
+ Response 200
    ```json
    [
        {
            "id": ,
            "firstName": "",
            "lastName": "",
            "email": "",
            "phone": ""
        },
        {
            "id": ,
            "firstName": "",
            "lastName": "",
            "email": "",
            "phone": ""
        },
        ...
    ]
    ```