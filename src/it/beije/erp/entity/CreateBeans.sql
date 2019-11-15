/* FIX: nome della tabella user */

CREATE TABLE `computer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(45) NOT NULL,
  `brand` VARCHAR(20) NOT NULL,
  `cpu` VARCHAR(15) NOT NULL,
  `ram` INT NOT NULL,
  `hard_disk` VARCHAR(10) NOT NULL,
  `serial_number` VARCHAR(45) NOT NULL,
  `operating_system` VARCHAR(20) NOT NULL,
  `availability` TINYINT NOT NULL,
  `note` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `serial_number_UNIQUE` (`serial_number` ASC))ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
  
  
  
CREATE TABLE `assignment` (
  	`id` INT NOT NULL AUTO_INCREMENT,
 	 `delivery` DATE NOT NULL,
 	 `return_date` DATE NULL,
  	`note` VARCHAR(45) NULL,
  	`id_user` INT NOT NULL,
  	`id_computer` INT NOT NULL,
  	PRIMARY KEY (`id`),
  	INDEX `id_user_idx` (`id_user` ASC),
  	INDEX `id_computer_idx` (`id_computer` ASC),
  	CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `beijedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  	CONSTRAINT `id_computer`
    FOREIGN KEY (`id_computer`)
    REFERENCES `beijedb`.`computer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COLLATE = utf8_bin;
	 

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
