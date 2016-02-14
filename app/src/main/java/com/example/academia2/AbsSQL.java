package com.example.academia2;

public abstract class AbsSQL {
	protected String DATABASE_NAME;
	protected String nm_tabela;
	protected String sqlCriarTabela;
	protected String[] colunas; // colunas da tabela fora o id
	protected abstract void iniciar(); // teste
}
