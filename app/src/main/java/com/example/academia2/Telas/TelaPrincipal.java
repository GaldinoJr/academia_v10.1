package com.example.academia2.Telas;
// Vers�o 3.0
import java.util.ArrayList;

import com.example.academia2.Dominio.CorGrupos;
import com.example.academia2.Dominio.Exercicio;
import com.example.academia2.Dominio.GrupoMuscular;
import com.example.academia2.BaseAdapter.GrupoMuscularBaseAdapter;
import com.example.academia2.R;
import com.example.academia2.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class TelaPrincipal extends Activity implements View.OnTouchListener, OnClickListener {
	//private String grupo;
	private Button btnVirarFotoPrinc,
					btnVirarFotoPrinc2;
	private Intent intent;
	private  String[] vetSgrupos;
	private String[] vetor;
	private float X1, X2, deltaX;
	private ImageView iVprincipal;
	private static final int LIMITE_MINIMO = 50;
	private static final int TOLERANCIA = 0;
	private String grupo;
	float Y1, Y2;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_principal);
		ArrayList<GrupoMuscular> image_details = GetSearchResults();
		// 
		btnVirarFotoPrinc = (Button)findViewById(id.btnVirarFotoPrinc);
		btnVirarFotoPrinc2 = (Button)findViewById(id.btnVirarFotoPrinc2);
		btnVirarFotoPrinc.setOnClickListener(this);
		btnVirarFotoPrinc2.setOnClickListener(this);
		//
			iVprincipal = (ImageView)findViewById(id.image);
			 if (iVprincipal != null) {
			       iVprincipal.setOnTouchListener (this);
			    }
		//
		
		final ListView lv1 = (ListView) findViewById(R.id.list);
		
		lv1.setAdapter(new GrupoMuscularBaseAdapter(this, image_details));
		
		lv1.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) 
			{
				Object o = lv1.getItemAtPosition(position);
				
				GrupoMuscular obj_itemDetails = (GrupoMuscular)o;
				grupo = obj_itemDetails.getNome();
				Exercicio exercicio = new Exercicio();
				exercicio.ordenarVetores(grupo);
				vetor = exercicio.getVetCorrespondente();
				if(vetor == null)
				{
					Toast.makeText(TelaPrincipal.this, "Não à exercicios para este grupo", Toast.LENGTH_SHORT);
					 Intent intent = new Intent(Intent.ACTION_MAIN); 
					 finish();
				}
            	chamarTelaCorrespondeteAoGrupo(vetor, grupo);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_principal, menu);
		return true;
	}
	

	public void chamarTelaCorrespondeteAoGrupo(String[] vetExe, String grupo)
	{ 
		Bundle b;
		
		b=new Bundle();
		b.putStringArray("exe", vetExe);
		intent = new Intent();
        // Para chamar a pr�xima tela tem que dizer qual e a tela atual, e dpois a pr�xima tela( a que vai ser chamada)
        intent.setClass(TelaPrincipal.this, TelaListaExercicios.class);
		intent.putExtras(b);
		intent.putExtra("grupo", grupo);
		startActivity(intent); // chama a pr�xima tela
        finish(); // Encerra a tela atual
	}
	
	
	
	public ArrayList<GrupoMuscular> GetSearchResults()
	{
		int i, qtdRegistro;
		Integer[] vetIndice;
		
		GrupoMuscular grupoMuscular;
		grupoMuscular = new GrupoMuscular();
		// Ordena em ordem alfabetica os vetores
		vetSgrupos = grupoMuscular.getVetorGrupos();
		ArrayList<GrupoMuscular> results = new ArrayList<GrupoMuscular>();
		qtdRegistro = vetSgrupos.length;
		
		vetIndice = new Integer[qtdRegistro];
		for(i = 0; i < qtdRegistro; i++)
		{
			vetIndice[i] = i;
			grupoMuscular = new GrupoMuscular();
			grupoMuscular.setNome(vetSgrupos[i]);
			grupoMuscular.setIdImage(i+1);
			results.add(grupoMuscular);
		}// for
		return results;
	}
	public boolean onTouch (View v, MotionEvent ev) 
	{
		CorGrupos corGrupos = new CorGrupos();
	    boolean handledHere = false;
	    final int action = ev.getAction();
	    final int evX = (int) ev.getX();
	    final int evY = (int) ev.getY();
	    int nextImage = -1;			// resource id of the next image to display
	    ImageView imageView = (ImageView) findViewById (R.id.image);
	    if (imageView == null) 
	    	return false;
	    Integer tagNum = (Integer) imageView.getTag ();
	    
	    int currentResource = (tagNum == null) ? R.drawable.principal_frente : tagNum.intValue ();
	    switch (action) 
	    {
	    case MotionEvent.ACTION_DOWN : // clicou na tela
	    	if (currentResource == R.drawable.principal_frente) {
	    		nextImage = R.drawable.principal_frente_precionar2;
                handledHere = true;
	        } 
	    	else 
	    	{
				nextImage = R.drawable.principal_tras_precionar;
				handledHere = true;
	    	}
	       break;

	    case MotionEvent.ACTION_UP : // soltou o clique
	    	 int touchColor;
	    	
	    	 if(currentResource == R.drawable.principal_frente || currentResource == R.drawable.principal_frente_precionar2)
	    		touchColor = getHotspotColor (R.id.image_areas, evX, evY);
	    	 else
	    		touchColor = getHotspotColor (R.id.image_areas_tras, evX, evY);

	    	 // VERIFICAR COR TOCADA
	    	 grupo = corGrupos.verificarMusculoTocado(touchColor, TOLERANCIA);
	    	 if(grupo.equals(""))
	    	 {
	    		 if (currentResource == R.drawable.principal_frente || currentResource == R.drawable.principal_frente_precionar2) // troca a imagem 
	    			nextImage = R.drawable.principal_frente;
	  			else
	  				nextImage = R.drawable.principal_tras; 

	    		 handledHere = true;
	    		 break;
	    	 }
			 Exercicio exercicio = new Exercicio();
		     exercicio.ordenarVetores(grupo);
			 vetor = exercicio.getVetCorrespondente();
			 if(vetor == null)
			 {
				Toast.makeText(TelaPrincipal.this, "N�o � exercicios para este grupo", Toast.LENGTH_SHORT);
				Intent intent = new Intent(Intent.ACTION_MAIN); 
				finish();
			 }
         	chamarTelaCorrespondeteAoGrupo(vetor, grupo);
			
         	
	       handledHere = true; 
	       break;
	    default:
	       handledHere = false;
	    } // end switch
	   
	    if (handledHere) {
	 
	       if (nextImage > 0) {
	          imageView.setImageResource (nextImage);
	          imageView.setTag (nextImage);
	       }
	    }
	    return handledHere;
	}   

	public int getHotspotColor (int hotspotId, int x, int y) {
		int teste;
	    ImageView img = (ImageView) findViewById (hotspotId);
	    if (img == null) {
	       Log.d ("ImageAreasActivity", "Imagem n�o encontrada");
	       return 0;
	    } else {
	      img.setDrawingCacheEnabled(true); 
	      Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
	      if (hotspots == null) {
	         Log.d ("ImageAreasActivity", "imagem n�o foi criada");
	         return 0;
	      } else {
	        img.setDrawingCacheEnabled(false);
	        // Testa se vai conseguir pegar a cor da tela a partir do toque
	        try
	        {
	        	teste = hotspots.getPixel(x, y);
	        }
	        catch(Exception e) // n�o conseguiu 
	        {
	        	teste = 0; // retorna zero
	        }
	        	
	        return teste;
	        		
	      }
	    }
	}

	@Override
	public void onClick(View v) {
		if(v == btnVirarFotoPrinc || v == btnVirarFotoPrinc2)
		{
			int proximaImagem = -1;
			ImageView imagemAtual = (ImageView) findViewById (R.id.image);
			if(imagemAtual == null) // n�o tem imagem na tela?
				return; // volta para onde estava 
		    Integer tagNum = (Integer) imagemAtual.getTag ();
		    int idImagemAtual = (tagNum == null) ? R.drawable.principal_frente : tagNum.intValue ();
		    int teste = R.drawable.principal_tras;
			if(idImagemAtual ==  R.drawable.principal_frente) // troca a imagem 
				proximaImagem = R.drawable.principal_tras;  
			else
				proximaImagem = R.drawable.principal_frente;
			
			 if (proximaImagem > 0) {
				 imagemAtual.setImageResource (proximaImagem);
				 imagemAtual.setTag (proximaImagem);
		       }
		}
	}

	public void onBackPressed() // voltar?
	{
		
			Intent intent = new Intent();
	        // Para chamar a pr�xima tela tem que dizer qual e a tela atual, e dpois a pr�xima tela( a que vai ser chamada)
	        intent.setClass(TelaPrincipal.this, TelaPrincipal02.class);
			startActivity(intent); // chama a pr�xima tela(tela anterior)
	        finish();
		
	}	
}// end class
