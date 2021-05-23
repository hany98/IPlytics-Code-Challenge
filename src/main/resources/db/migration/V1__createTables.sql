create table patent (
    publication_number 	VARCHAR(255) 	NOT NULL 	PRIMARY KEY,
    publication_date 	DATE 			NOT NULL,
    title 				VARCHAR(255) 	NOT NULL,
    description 		LONGTEXT 		NOT NULL	DEFAULT '',
	creation_date		DATE 			NULL,
    modification_date	DATE 			NULL
);

create table standard (
    standard_id 		VARCHAR(255) 	NOT NULL 	PRIMARY KEY,
    name 				VARCHAR(255) 	NOT NULL,
    description 		LONGTEXT 		NOT NULL	DEFAULT '',
	creation_date		DATE 			NULL,
    modification_date	DATE 			NULL
);

create table declaration (
	id 					INT 			NOT NULL 	AUTO_INCREMENT	PRIMARY KEY,
	standard_id 		VARCHAR(255) 	NOT NULL,
	publication_number 	VARCHAR(255) 	NOT NULL,
	description			LONGTEXT 		NOT NULL 	DEFAULT '',
	creation_date		DATE 			NULL,
    modification_date	DATE 			NULL
);