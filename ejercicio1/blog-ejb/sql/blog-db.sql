CREATE TABLE articulo (
	id INTEGER NOT NULL 
		GENERATED ALWAYS AS IDENTITY 
		(START WITH 1, INCREMENT BY 1),
	titulo VARCHAR(100) NOT NULL,
	contenido VARCHAR(500),
	PRIMARY KEY(id)
);

CREATE TABLE comentario (
	id INTEGER NOT NULL 
		GENERATED ALWAYS AS IDENTITY 
		(START WITH 1, INCREMENT BY 1),
	articulo_id INTEGER NOT NULL,
	usuario VARCHAR(20),
	contenido VARCHAR(250),
	PRIMARY KEY (id),
	FOREIGN KEY (articulo_id) REFERENCES articulo(id)
);

INSERT INTO articulo(titulo, contenido) VALUES('Artículo #1', 'Contenido del Artículo #1');
INSERT INTO articulo(titulo, contenido) VALUES('Artículo #2', 'Contenido del Artículo #2');
INSERT INTO articulo(titulo, contenido) VALUES('Artículo #3', 'Contenido del Artículo #3');