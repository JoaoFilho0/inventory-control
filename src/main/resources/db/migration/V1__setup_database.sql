DROP TABLE IF EXISTS compra;
DROP TABLE IF EXISTS produtos;
DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE produtos (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  quantidade INT,
  preco DOUBLE NOT NULL,
  descricao TEXT,
  PRIMARY KEY (id)
);

CREATE TABLE compra (
  id INT NOT NULL AUTO_INCREMENT,
  id_produto INT,
  id_cliente INT,
  data_hora DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (id_produto) REFERENCES produtos(id),
  FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);
