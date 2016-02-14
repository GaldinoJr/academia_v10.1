package com.example.academia2;


import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class SQLgrupo extends AbsSQL{
	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaDB";
		nm_tabela = "grupos";
		sqlCriarTabela = "CREATE TABLE grupos ( " +
	            "id INTEGER PRIMARY KEY, " +
	            "nome TEXT )"; 
	}
	
	private static final String KEY_ID = "id";
	private static final String KEY_NOME = "nome";
	private static final String[] colunas = {KEY_NOME};
	private SQL db;
	
	private Map<String, String> mapGrupo;
	
	private String sId;
	
	public SQLgrupo(Context context){
		iniciar();
		db  = new SQL(context, DATABASE_NAME, nm_tabela,colunas, sqlCriarTabela );
	}
	
	public void IncluirGrupo(String nome)
	{
		mapGrupo = new HashMap<String, String>();
		mapGrupo.put(KEY_NOME, nome);
		db.addRegistro(mapGrupo);
	}
	
	public GrupoMuscular pesquisarGrupo(int id)
	{
		String[] colunasBusca = {KEY_ID, KEY_NOME};
		sId = String.valueOf(id);
		Map<String, String> mapDados = new HashMap<String, String>();
		mapDados = db.buscarRegistro(KEY_ID,sId, colunasBusca);
		GrupoMuscular grupoMuscular = new GrupoMuscular();
		if(mapDados == null) // não encontrou o livro?
			return grupoMuscular = null; // retorna indicando que o livro não foi encontrado
		
		grupoMuscular.setID(mapDados.get(KEY_ID));
		grupoMuscular.setNome(mapDados.get(KEY_NOME));
	    return grupoMuscular;

	}
}
