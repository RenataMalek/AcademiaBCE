CREATE TABLE IF NOT EXISTS `atividades` (
  `ID_ATIVIDADE` int(6) NOT NULL AUTO_INCREMENT,
  `NOME_ATIVIDADE` varchar(20) CHARACTER SET utf8 NOT NULL,
  `QTD_SECAO_ATIVIDADE` int(2) NOT NULL,
  `QTD_REPETICAO_ATIVIDADE` int(2) NOT NULL,
  `TEMPO_DURACAO_ATIVIDADE` time NOT NULL,
  PRIMARY KEY (`ID_ATIVIDADE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `atividades_treino`
--

CREATE TABLE IF NOT EXISTS `atividades_treino` (
  `ID_TREINO_FK` int(6) NOT NULL,
  `ID_ATIVIDADE_FK` int(6) NOT NULL,
  PRIMARY KEY (`ID_TREINO_FK`,`ID_ATIVIDADE_FK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `ID_CLIENTE` int(6) NOT NULL AUTO_INCREMENT,
  `CPF_CLIENTE` varchar(14) CHARACTER SET utf8 NOT NULL,
  `NOME_CLIENTE` varchar(50) CHARACTER SET utf8 NOT NULL,
  `EMAIL_CLIENTE` varchar(50) CHARACTER SET utf8 NOT NULL,
  `TELEFONE_CLIENTE` varchar(14) CHARACTER SET utf8 NOT NULL,
  `RUA_CLIENTE` varchar(20) CHARACTER SET utf8 NOT NULL,
  `BAIRRO_CLIENTE` varchar(15) CHARACTER SET utf8 NOT NULL,
  `NUMERO_CASA_CLIENTE` int(6) NOT NULL,
  `COMPLEMENTO_CLIENTE` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `CEP` int(8) NOT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cobranca`
--

CREATE TABLE IF NOT EXISTS `cobranca` (
  `ID_COBRANCA` int(6) NOT NULL AUTO_INCREMENT,
  `ID_CONTRATO_FK` int(6) NOT NULL,
  `DIA_VCTO_COBRANCA` date NOT NULL,
  `VALOR_COBRANCA` double NOT NULL,
  `NUM_PARCELA_COBRANCA` int(2) NOT NULL,
  `PAGO_COBRANCA` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_COBRANCA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contrato`
--

CREATE TABLE IF NOT EXISTS `contrato` (
  `ID_CONTRATO` int(6) NOT NULL AUTO_INCREMENT,
  `ID_CLIENTE_FK` int(6) NOT NULL,
  `DATA_CONTRATO` date NOT NULL,
  `VALOR_MENSAL_CONTRATO` double NOT NULL,
  `PARCELAS` int(2) NOT NULL,
  `QTD_TREINOS` int(2) NOT NULL,
  PRIMARY KEY (`ID_CONTRATO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `participacao_treino`
--

CREATE TABLE IF NOT EXISTS `participacao_treino` (
  `ID_CONTRATO_FK` int(6) NOT NULL,
  `ID_TREINO_FK` int(6) NOT NULL,
  PRIMARY KEY (`ID_CONTRATO_FK`,`ID_TREINO_FK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `treino`
--

CREATE TABLE IF NOT EXISTS `treino` (
  `ID_TREINO` int(6) NOT NULL AUTO_INCREMENT,
  `TIPO_TREINO` varchar(15) CHARACTER SET utf8 NOT NULL,
  `NIVEL_TREINO` varchar(10) CHARACTER SET utf8 NOT NULL,
  `QTD_ATIVD_TREINO` int(2) NOT NULL,
  PRIMARY KEY (`ID_TREINO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


select * from cliente;

select * from atividades;



ALTER TABLE `cliente` DROP `RUA_CLIENTE`, DROP `BAIRRO_CLIENTE`, DROP `NUMERO_CASA_CLIENTE`, DROP `COMPLEMENTO_CLIENTE`, DROP `CEP`;




ALTER TABLE `cliente` ADD `ENDERECO_CLIENTE` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `TELEFONE_CLIENTE`;













select * from atividades;



select * from atividades_treino;



select * from cobranca;

select * from contrato;

select * from participacao_treino;


select * from treino;


INSERT INTO `academia`.`recepcionista` (`CPF_CLIENTE`, `NOME_CLIENTE`, `EMAIL_CLIENTE`, `TELEFONE_CLIENTE`, `ENDERECO_CLIENTE`) VALUES ('000.000.00.12', 'Renata dos Santos Malek', 'renata.academia@academia.com', '11 3210-9876', 'Rua bla, bairro bla numero bla');



select * from cliente;





select * from pet;



use academia;

create table recepcionista(
id_recepcionista bigint primary key auto_increment,
nome varchar(255),
cpf varchar(11),
telefone varchar(30),
codigoAcesso varchar(200)
);

create table treinador (
id_treinador bigint primary key auto_increment,
nome varchar(255),
cpf varchar(11),
telefone varchar(30),
codigoAcesso varchar(200)
);

insert into recepcionista(nome, cpf, telefone, codigoAcesso) values
('Recepcionista', '628498320', '72842384', 'acessar');
insert cliente(nome, email, cpf, telefone, endereco) values
('Renata', 'renata@mail.com', '01234567890', '1138918912891', 'Rua nao sei');
show tables;



