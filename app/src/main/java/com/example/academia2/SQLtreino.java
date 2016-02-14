package com.example.academia2;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;

public class SQLtreino extends AbsSQL{

	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaBD";
		nm_tabela = "treinos";
		sqlCriarTabela = "CREATE TABLE treinos ( " +
	            "id INTEGER PRIMARY KEY, " +
	            "nome TEXT )"; 
	}
	
	private static final String KEY_ID = "id";
	private static final String KEY_NOME = "nome";
	private static final String[] colunas = {KEY_NOME};
	private SQL db;
	
	private Map<String, String> mapTreino;
	
	private String sId;
	
	public SQLtreino(Context context){
		iniciar();
		db  = new SQL(context, DATABASE_NAME, nm_tabela,colunas, sqlCriarTabela );
	}
	
	public void IncluirTreino(String nome)
	{
		mapTreino = new HashMap<String, String>();
		mapTreino.put(KEY_NOME, nome);
		db.addRegistro(mapTreino);
	}
	
	public Treino pesquisarTreino(int id)
	{
		String[] colunasBusca = {KEY_ID, KEY_NOME};
		sId = String.valueOf(id);
		Map<String, String> mapDados = new HashMap<String, String>();
		mapDados = db.buscarRegistro(KEY_ID,sId, colunasBusca);
		Treino treino = new Treino();
		if(mapDados == null) // não encontrou o livro?
			return treino = null; // retorna indicando que o livro não foi encontrado
		
		treino.setID(mapDados.get(KEY_ID));
		treino.setNome(mapDados.get(KEY_NOME));
	    return treino;

	}
	
	public List<Treino> pesquisarTodosTreinos()
	{
		List<Treino> ltreino = new LinkedList<Treino>();
		Treino treino;
		String[] colunasBusca = {KEY_ID, KEY_NOME};
		 List<Map<String, String>> LMtreinos = new LinkedList<Map<String, String>>();
		 LMtreinos = db.buscarTodosRegistros(colunasBusca);
		 for(int i = 0; i< LMtreinos.size();i++)
		 {
			treino = new Treino();
			treino.setID(LMtreinos.get(i).get(KEY_ID));
			treino.setNome(LMtreinos.get(i).get(KEY_NOME));
			ltreino.add(treino);
		 }
		 return ltreino;
	}
}
