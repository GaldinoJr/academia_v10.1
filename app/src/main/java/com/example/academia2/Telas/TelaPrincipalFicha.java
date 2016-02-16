package com.example.academia2.Telas;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.academia2.Controler.Controler;
import com.example.academia2.Dominio.EntidadeDominio;
import com.example.academia2.R;
import com.example.academia2.R.id;
import com.example.academia2.Core.Impl.SqlDAO.SQLtreino;
import com.example.academia2.Dominio.Treino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TelaPrincipalFicha extends Activity implements OnClickListener {

	private int i;
	private Button btnAddTreino;
	private EditText txtNomeTreino;
	private ListView listTreinos;
	//
	//private SQLtreino dbTreino;
	private Treino treino;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_principal_ficha);
		//

		//
		btnAddTreino = (Button)findViewById(id.btnAddTreino);
		txtNomeTreino = (EditText)findViewById(id.txtNomeTreino);
		listTreinos = (ListView)findViewById(id.listTreinos);
		//
		btnAddTreino.setOnClickListener(this);
		//
		atualizarListTreinos();
		//
		listTreinos.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) 
			{
				String nome;
				Object o = listTreinos.getItemAtPosition(position);
				nome = o.toString();
				//Toast.makeText(TelaPrincipalFicha.this, "nome: " + nome, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				// Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
				intent.setClass(TelaPrincipalFicha.this, TelaFichaListExercicios.class);
				intent.putExtra("nomeTreino", nome);
				startActivity(intent); // chama a próxima tela
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
		getMenuInflater().inflate(R.menu.tela_principal_ficha, menu);
		return true;
	}

	
	private void atualizarListTreinos()
	{
		/*
		Treino trein = new Treino();
		dbTreino = new SQLtreino(this);
		trein = dbTreino.pesquisarTreino(1);
		Toast.makeText(TelaPrincipalFicha.this, trein.getID() + ", " + trein.getNome(), Toast.LENGTH_SHORT).show();
		*/
		//dbTreino = new SQLtreino(this);
//		List<Treino> lTreino = new LinkedList<Treino>();
//		lTreino = dbTreino.pesquisarTodosTreinos();

		treino = new Treino();
		List<Treino> lTreino = new LinkedList<Treino>();
		lTreino = treino.converteEntidadeEmClasse(treino.operar(this,true, Controler.DF_CONSULTAR,treino), Treino.class);

		//lTreino = (Treino)treino.operar(this,true, Controler.DF_CONSULTAR);
		if(lTreino == null)
			return;
		else
			if(lTreino.isEmpty())
				return;
		String[] vetSTreino = new String[lTreino.size()];
		for(i = 0; i< lTreino.size();i++)
			vetSTreino[i] = lTreino.get(i).getNome();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, vetSTreino); 

		listTreinos.setAdapter(adapter);
		
	}
	@Override
	public void onClick(View view) {
		if(view == btnAddTreino)
		{ // vai add um novo treino?
			String nomeTreino;
			nomeTreino = txtNomeTreino.getText().toString();
			if(nomeTreino.equals("")) // não digitou nome nenhum?
			{
				Toast.makeText(TelaPrincipalFicha.this, "Digite um nome para o treino", Toast.LENGTH_SHORT).show();
				return;
			}
			// se chegou até aqui digitou um nome, então cadastrar o novo treino no banco, e chamar o método para atualizar a list
			treino = new Treino();
			treino.setNome(nomeTreino);
			treino.operar(this, true, Controler.DF_SALVAR, treino);
			atualizarListTreinos();
			txtNomeTreino.setText("");
		}
		
	}	
	public void onBackPressed() // voltar?
	{
		
			Intent intent = new Intent();
	        // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
	        intent.setClass(TelaPrincipalFicha.this, TelaPrincipal02.class);
			startActivity(intent); // chama a próxima tela(tela anterior)
	        finish();
		
	}

}
