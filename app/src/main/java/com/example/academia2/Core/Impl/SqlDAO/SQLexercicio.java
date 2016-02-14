package com.example.academia2.Core.Impl.SqlDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import android.content.Context;
import android.text.TextUtils;

import com.example.academia2.Dominio.ConfiguracaoSistema;
import com.example.academia2.Dominio.EntidadeDominio;
import com.example.academia2.Dominio.Exercicio;
import com.example.academia2.Dominio.GrupoMuscular;

public class SQLexercicio extends AbsSQL{
	
	private static final String Col_cd_exercicio = "cd_exercicio";
	private static final String Col_ds_exercicio = "ds_exercicio";
	private static final String Col_cd_grupo = "cd_grupo";
	private static final String[] colunas = {Col_ds_exercicio,Col_cd_grupo};
	private static final String[] colunasBusca = {Col_cd_exercicio,Col_ds_exercicio,Col_cd_grupo};
	private SQL db;
	
	private Map<String, String> mapExercicio;
	private String sId;
	private Exercicio exercicio;
	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaDB";
		nomeTabela	 = "tb_exercicio";
		sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
				Col_cd_exercicio + " INTEGER PRIMARY KEY, " +
				Col_ds_exercicio + " TEXT, "+
				Col_cd_grupo + " INTEGER FOREIGN KEY )";
	}

	public SQLexercicio(Context context){
		iniciar();
		//db  = new SQL(context, DATABASE_NAME, nm_tabela,colunas, sqlCriarTabela ); // GANHO DE PERFORMANCE NO CÓDIGO ORIGINAL
		db  = SQL.getInstance(context, DATABASE_NAME );
		db.popularInfo( nomeTabela, colunas, sqlCriarTabela);
	}

	
//	public Exercicio pesquisarExercicio(int id)
//	{
//		String[] colunasBusca = {Col_cd_exercicio, Col_ds_exercicio, Col_cd_grupo};
//		sId = String.valueOf(id);
//		Map<String, String> mapDados = new HashMap<String, String>();
//		mapDados = db.buscarRegistro(Col_cd_exercicio,sId, colunasBusca);
//		Exercicio exercicio = new Exercicio();
//		if(mapDados == null) // não encontrou o exercício?
//			return exercicio = null; // retorna indicando que o exercício não foi encontrado
//
//		exercicio.setID(mapDados.get(Col_cd_exercicio));
//		exercicio.setNome(mapDados.get(Col_ds_exercicio));
//		exercicio.setIdGrupo(mapDados.get(Col_cd_grupo));
//	    return exercicio;
//
//	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		try {
			exercicio =  (Exercicio)entidade;
			mapSql = new HashMap<String, String>();

			mapSql.put(Col_ds_exercicio, String.valueOf(exercicio.getNome()));
			mapSql.put(Col_cd_grupo, String.valueOf(exercicio.getIdGrupo()));
			db.addRegistro(mapSql);
			//db.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	@Override
	public void excluir(EntidadeDominio entidade) {

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		int i;
		exercicio =  (Exercicio)entidade;
		try
		{
			String query = "SELECT " + Col_cd_exercicio;
			for(i = 1; i < colunasBusca.length; i++)
			{
				query += ", " + colunasBusca[i];
			}
			query += " FROM " + nomeTabela + " WHERE 1 = 1";
			if (!TextUtils.isEmpty(exercicio.getID()))
				query += " AND " + Col_cd_exercicio + "= '" + exercicio.getID() + "'";

			listSql = new ArrayList<EntidadeDominio>();

			listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
			listMapSql = db.pesquisarComSelect(query, colunasBusca);
			//db.close();
			for(i = 0; i< listMapSql.size();i++)
			{
				Exercicio e = new Exercicio();
				// ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
				e.setID(listMapSql.get(i).get(colunasBusca[0]));
				e.setNome(listMapSql.get(i).get(colunasBusca[1]));
				e.setIdGrupo(listMapSql.get(i).get(colunasBusca[2]));

				listSql.add(e);
			}
			if(listSql.size() > 0)
				return listSql;
			else
				return null;
		}
		catch(Exception e){ e.printStackTrace(); }

		return null;
	}
}
