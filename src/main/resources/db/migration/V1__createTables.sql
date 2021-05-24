create table patent (
    publication_number 	VARCHAR(255) 	NOT NULL 	PRIMARY KEY,
    publication_date 	DATE 			NOT NULL,
    title 				VARCHAR(255) 	NOT NULL,
    description 		LONGTEXT 		NOT NULL	DEFAULT '',
	creation_date		DATETIME 			NULL,
    modification_date	DATETIME 			NULL
);

create table standard (
    standard_id 		VARCHAR(255) 	NOT NULL 	PRIMARY KEY,
    name 				VARCHAR(255) 	NOT NULL,
    description 		LONGTEXT 		NOT NULL	DEFAULT '',
	creation_date		DATETIME 			NULL,
    modification_date	DATETIME 			NULL
);

create table declaration (
	id 					INT 			NOT NULL 	AUTO_INCREMENT	PRIMARY KEY,
	publication_number 	VARCHAR(255) 	NOT NULL,
	standard_id 		VARCHAR(255) 	NOT NULL,
	description			LONGTEXT 		NOT NULL 	DEFAULT '',
	creation_date		DATETIME 			NULL,
    modification_date	DATETIME 			NULL,
	FOREIGN KEY (`publication_number`) REFERENCES `patent` (`publication_number`),
	FOREIGN KEY (`standard_id`) REFERENCES `standard` (`standard_id`)
);