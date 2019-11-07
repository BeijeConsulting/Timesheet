/* FIX: nome della tabella user */

CREATE TABLE bank_credentials(
	id INT NOT NULL AUTO_INCREMENT,
	id_user INT NOT NULL,
    accountholder VARCHAR(100) NOT NULL,
    iban VARCHAR(27) NOT NULL,
    swift VARCHAR(15) NULL,
    start_date DATE NOT NULL,
    end_date DATE NULL,
    notes VARCHAR(200) NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_user) REFERENCES beijedb.user(id)
);

CREATE TABLE address(   
	id INT NOT NULL AUTO_INCREMENT,     
	id_user INT NOT NULL,
	street VARCHAR(100) NOT NULL,        
	city VARCHAR(45) NOT NULL,     
	province VARCHAR(2) NOT NULL,     
	cap VARCHAR(5) NOT NULL,    
	country CHAR(2) NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NULL,       
	   
	PRIMARY KEY(id),     
	FOREIGN KEY(id_user) REFERENCES beijedb.user(id)  
);

CREATE TABLE contratto(
	id INT NOT NULL AUTO_INCREMENT,
    id_user INT NOT NULL,
	contract_type CHAR(1) NOT NULL,
    ccnl VARCHAR(50) NULL,
    liv SMALLINT  NULL,
    minimo_contrattuale DOUBLE  NULL,
    superminimo DOUBLE  NULL,
    retribuzione_mensile DOUBLE  NULL,
    ral DOUBLE  NULL,
    netto_mensile DOUBLE NULL,
    costo_interno DOUBLE NOT NULL DEFAULT 0,
    note VARCHAR(200) NULL,
    
	PRIMARY KEY(id),
	FOREIGN KEY(contract_type) REFERENCES beijedb.contract_type(cod),
    FOREIGN KEY(id_user) REFERENCES beijedb.user(id)
);

CREATE TABLE contract_type(
    cod CHAR(1) NOT NULL,													/* PK (I, D, C, S, P) */
    description VARCHAR(30) NOT NULL,
    PRIMARY KEY(cod)
);

/* Versione con ENUM come PK */
CREATE TABLE contract_type(
    cod ENUM("I", "D", "C", "S", "P") NOT NULL,								/* PK (I, D, C, S, P) */
    description VARCHAR(30) NOT NULL,
    PRIMARY KEY(cod)
);
