/*
Tipo 1 = Administrador
Tipo 2 = Leitor
*/
CREATE TABLE `pessoa` (
  `idpessoa` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `tipo` INT NOT NULL,
  `nome` TEXT NOT NULL,
  `email` TEXT NOT NULL,
  `senha` TEXT NOT NULL
);

INSERT INTO `pessoa` (idpessoa, tipo, nome, email, senha) VALUES (NULL, 1, 'Admin 01', 'admin01@email.com', 'admin01');
INSERT INTO `pessoa` (idpessoa, tipo, nome, email, senha) VALUES (NULL, 1, 'Admin 02', 'admin02@email.com', 'admin02');
INSERT INTO `pessoa` (idpessoa, tipo, nome, email, senha) VALUES (NULL, 2, 'Leitor 01', 'leitor01@email.com', 'leitor01');
INSERT INTO `pessoa` (idpessoa, tipo, nome, email, senha) VALUES (NULL, 2, 'Leitor 02', 'leitor02@email.com', 'leitor02');

CREATE TABLE `categoria` (
  `idcategoria` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `categoria` TEXT NOT NULL
);

INSERT INTO `categoria` (`idcategoria`, `categoria`) VALUES (NULL, 'Esportes');
INSERT INTO `categoria` (`idcategoria`, `categoria`) VALUES (NULL, 'Politica');
INSERT INTO `categoria` (`idcategoria`, `categoria`) VALUES (NULL, 'Games');

CREATE TABLE `post` (
  `idpost` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `idcategoria` INTEGER NOT NULL,
  `idpessoa` INTEGER NOT NULL,
  `titulo` TEXT NOT NULL,
  `texto` TEXT NOT NULL
);

INSERT INTO `post` (idpost, idcategoria, idpessoa, titulo, texto) VALUES (NULL, 1, 1, 'Jogo de Futebol', 'Neste ultimo final de semana, os times jogaram ...');
INSERT INTO `post` (idpost, idcategoria, idpessoa, titulo, texto) VALUES (NULL, 1, 2, 'Mundial de Voley', 'O Brasil venceu a seleção da Servia e Montenegro');
INSERT INTO `post` (idpost, idcategoria, idpessoa, titulo, texto) VALUES (NULL, 3, 1, 'Lançamento de Call Of Dutty XXI', 'Nesta semana será lançado o novo jogo da série Call Of Dutty');

CREATE TABLE `comentario` (
  `idcomentario` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `idpost` INTEGER NOT NULL,
  `idpessoa` INTEGER NOT NULL,
  `comentario` TEXT NOT NULL
);

INSERT INTO `comentario` (idcomentario, idpost, idpessoa, comentario) VALUES (NULL, 1, 3, 'Vai Brasil!!1!');
INSERT INTO `comentario` (idcomentario, idpost, idpessoa, comentario) VALUES (NULL, 2, 4, 'Giba jogou?');
INSERT INTO `comentario` (idcomentario, idpost, idpessoa, comentario) VALUES (NULL, 2, 1, 'Sim, foi um dos maiores pontuadores.');
INSERT INTO `comentario` (idcomentario, idpost, idpessoa, comentario) VALUES (NULL, 3, 1, 'Meu fds vai jogando isso cara.');
INSERT INTO `comentario` (idcomentario, idpost, idpessoa, comentario) VALUES (NULL, 2, 1, 'Servia parece que nem estava em quadra.');
