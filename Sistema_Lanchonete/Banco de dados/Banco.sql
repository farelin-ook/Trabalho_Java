create DATABASE LanchoneteJava;

use LanchoneteJava;
create table Produto (
    codigo int,
    nome varchar(45),
    valor double,
    descricao varchar(200),
    primary key (codigo)
);

create table Venda (
    codVenda int,
    valorTotal double,
    primary key (codVenda)
);


create table VendaProduto(
    Venda int,
    produto int,
    foreign Key (Venda) references venda(codVenda),
    foreign Key (produto) references Produto(codigo)
);

create table Funcionario (
    idFuncionario int,
    nome varchar(45),
    senha varchar(45),
    cargo varchar(9),
    constraint funcao check(cargo = "Gerente" or cargo = "Vendedor"),
    primary key (Idfuncionario)
);

create table FuncVenda(
    funcionario int,
    venda int,
    dataVenda DATE,
    Foreign Key (funcionario) REFERENCES Funcionario(idFuncionario),
    Foreign Key (venda) REFERENCES Venda(codVenda)
);

--Importante insrtancioar pelo menos 1 gerente ao inicializar o sistema para se ter como logar--
INSERT into funcionario VALUES  (1, "Geraldo", "1234", "Gerente"),
                                (2, "Reginaldo", "4321", "Vendedor");
