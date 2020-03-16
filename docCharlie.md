
Controller ContractApi
===================

In Controller ContractApi:

-GET: List<Contract> getContractForUser(Long id): dato l'id di un utente in ingresso, ritorna la lista dei contratti corrispondenti presenti nel db.

-GET: Contract getContract(Long id): dato l'id di un contratto, ritorna la corrispondenza presente nel db.

-POST: Contract createContract(Long id, Contract contract): dato un id utente ed un contratto, aggiunge quest'ultimo nel db associandolo all'utente.

-PUT: Contract updateContract(Long id, Contract contract): dato un id contratto, modifica il record nel database con le informazioni dell'oggetto in ingresso.


Controller BankCredentialsApi
===================

In Controller BankCredentialsApi:

-GET: List<BankCredentials> getCredentialsForUser(Long id): dato l'id di un utente in ingresso, ritorna la lista delle coordinate bancarie corrispondenti presenti nel db.

-GET: BankCredentials getBankCredentials(Long id): dato l'id di una coordinata bancaria, ritorna la corrispondenza presente nel db.

-POST: BankCredentials createBankCredentials(Long id, BankCredentials bankCredentials): dato un id utente ed una coordinata bancaria, aggiunge quest'ultima nel db associandolo all'utente.

-PUT: BankCredentials updateBankCredential(Long id, BankCredentials bankCredentials): dato un id di una coordinata bancaria, modifica il record nel database con le informazioni dell'oggetto in ingresso.