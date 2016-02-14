package com.example.academia2;

import java.util.HashMap;
import java.util.Map;


import android.content.Context;

public class SQLexercicio extends AbsSQL{
	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaDB";
		nm_tabela = "exercicios";
		sqlCriarTabela = "CREATE TABLE exercicios ( " +
	            "id INTEGER PRIMARY KEY, " +
	            "nome TEXT, "+
	            "id_grupo INTEGER FOREIGN KEY )"; 
	}
	
	private static final String KEY_ID = "id";
	private static final String KEY_NOME = "nome";
	private static final String KEY_ID_GRUPO = "id_grupo";
	private static final String[] colunas = {KEY_NOME,KEY_ID_GRUPO};
	private SQL db;
	
	private Map<String, String> mapExercicio;
	
	private String sId;
	
	public SQLexercicio(Context context){
		iniciar();
		db  = new SQL(context, DATABASE_NAME, nm_tabela,colunas, sqlCriarTabela );
	}
	
	public void IncluirExercicio(String nome, String idGrupo)
	{
		mapExercicio = new HashMap<String, String>();
		mapExercicio.put(KEY_NOME, nome);
		mapExercicio.put(KEY_ID_GRUPO, idGrupo);
		db.addRegistro(mapExercicio);
	}
	
	public Exercicio pesquisarExercicio(int id)
	{
		String[] colunasBusca = {KEY_ID, KEY_NOME, KEY_ID_GRUPO};
		sId = String.valueOf(id);
		Map<String, String> mapDados = new HashMap<String, String>();
		mapDados = db.buscarRegistro(KEY_ID,sId, colunasBusca);
		Exercicio exercicio = new Exercicio();
		if(mapDados == null) // não encontrou o livro?
			return exercicio = null; // retorna indicando que o livro não foi encontrado
		
		exercicio.setID(mapDados.get(KEY_ID));
		exercicio.setNome(mapDados.get(KEY_NOME));
		exercicio.setIdGrupo(mapDados.get(KEY_ID_GRUPO));
	    return exercicio;

	}
}
