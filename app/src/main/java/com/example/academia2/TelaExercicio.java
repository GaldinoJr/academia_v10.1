package com.example.academia2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.academia2.R.id;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaExercicio extends Activity{

	private TextView txtNomeExe,
	txtNomeGrupo, 
	txtPrimario,
	txtSecundario,
	txtDescricao;
	private String nome,
		exe,
		CaminhoGif,
		grupo,
		primario,
		secundario,
		descricao,
		aux;
	private WebView wvExercicio;
	private ImageView imgCor;
	private String[] vetExe;
	private Integer idCor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_exercicio);
		
		// Associa o objeto
		txtNomeExe = (TextView)findViewById(com.example.academia2.R.id.txtDescriExe);
		txtNomeGrupo = (TextView)findViewById(com.example.academia2.R.id.txtNomeGrupo);
		txtPrimario =(TextView)findViewById(com.example.academia2.R.id.txtPrimario2);
		txtSecundario = (TextView)findViewById(com.example.academia2.R.id.txtSecundario);
		txtDescricao =(TextView)findViewById(com.example.academia2.R.id.txtDescricao);
		//
		wvExercicio = (WebView)findViewById(com.example.academia2.R.id.wvExercicio);
		imgCor = (ImageView)findViewById(id.imgCorTelaExer);
		// cria a intenção que vai receber os dados da tela 1
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        nome = dados.getStringExtra("nome");
        exe = dados.getStringExtra("exe");
        idCor = dados.getIntExtra("idCor", 0);
        
        // Recebe o conteudo do vetor que vai ser devolvido
        vetExe = (String[])dados.getSerializableExtra("exercicios");
        Bundle b=this.getIntent().getExtras();
        vetExe = b.getStringArray("vetExe");
        //
        grupo = dados.getStringExtra("grupo");
        // Define o caminho do gif
        CaminhoGif = "file:///android_asset/" + grupo +"/"+ nome + ".gif";
        
        // Devolve os conteudos	
        txtNomeExe.setText(exe);
        txtNomeGrupo.setText(grupo);
        imgCor.setImageResource(idCor);
        // Exibe a animação em gif
		wvExercicio.loadUrl(CaminhoGif);
        // 
		Documento documento = new Documento(this);
		primario =  documento.carregarArquivoTxt(grupo, nome, "Princ");
		if(primario == null)
			primario = txtPrimario.getText().toString();
		secundario = documento.carregarArquivoTxt(grupo, nome, "Sec");
		if(secundario == null)
			secundario = txtSecundario.getText().toString();
		aux = documento.carregarArquivoTxt(grupo, nome, "Descr");
		descricao = txtDescricao.getText().toString() + " ";
		if(aux != null)
			descricao += aux;
		else
			descricao += "Sem informações";
		//
		txtPrimario.setText(primario);
		txtSecundario.setText(secundario);
		txtDescricao.setText(descricao);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_exercicio, menu);
		return true;
	}

	public void onBackPressed()
	{
			Bundle b;
			Intent intent;
			
			b=new Bundle();
			b.putStringArray("exe", vetExe);
			intent = new Intent();
	        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
	        intent.setClass(TelaExercicio.this, TelaListaExercicios.class);
			intent.putExtras(b);
			intent.putExtra("grupo", grupo);
			startActivity(intent); // chama a próxima tela
	        finish();
		
	}	
}
