package com.example.academia2;

import com.example.academia2.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelaFichaListExercicios extends Activity implements OnClickListener {
	private Button btnAddEx;
	private String nmTreino;
	private TextView lblNmTreino;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_ficha_list_exercicios);
		// associa os objetos da tela
		btnAddEx = (Button)findViewById(id.btnAddExercicio);
		lblNmTreino = (TextView)findViewById(id.lblNmTreino);
		// dizer que os botões podem ser clicados
		btnAddEx.setOnClickListener(this);
		//
		// cria a intenção que vai receber os dados da tela 1
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        nmTreino = dados.getStringExtra("nomeTreino");
        //
        lblNmTreino.setText(nmTreino);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_ficha_list_exercicios, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		if(view == btnAddEx)
		{
			Toast.makeText(TelaFichaListExercicios.this, "clicou", Toast.LENGTH_SHORT).show();
		}
		
	}
	public void onBackPressed() // voltar?
	{
		
			Intent intent = new Intent();
	        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
	        intent.setClass(TelaFichaListExercicios.this, TelaPrincipalFicha.class);
			startActivity(intent); // chama a próxima tela(tela anterior)
	        finish();
		
	}


}
