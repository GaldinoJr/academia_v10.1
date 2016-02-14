package com.example.academia2;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.academia2.R.id;

public class ExercicioBaseAdapter  extends BaseAdapter {
	private static ArrayList<Exercicio> ExercicioArrayList;
	private LayoutInflater l_Inflater;
	
	// Da pra ordenar os ids, pelos ids
	private Integer[] imgId;
	
	
	
	// Construtor 1
	public ExercicioBaseAdapter(Context context, ArrayList<Exercicio> results, String grupo) 
	{
		Exercicio ex = new Exercicio();
		ex.ordenarVetores(grupo);
		imgId = orderAlfabeticamenteIdImagem(ex.getVetId());
		ExercicioArrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}
	// Construtor 2
	public ExercicioBaseAdapter()
	{
		
	}
	private Integer[] orderAlfabeticamenteIdImagem(Integer[]imgid)
	{
		int aux, 
			nTamanho,
			i;
		Boolean flgHouveTroca = false;
		nTamanho = imgid.length;
		do
		{
			flgHouveTroca = false;
			for(i = 0; i < nTamanho-1; i++)
			{
				if(imgid[i] > imgid[i+1]) // Atual � maior que o pr�ximo?
				{ // sim, ent�o troca
					aux = imgid[i];
					imgid[i] = imgid[i+1];
					imgid[i+1] = aux;
					flgHouveTroca = true; // indica que houve troca
				}
			}
		}while(flgHouveTroca);	
		return imgid;
	}

	// Conta quantos registros tem no array
	public int getCount() 
	{
		return ExercicioArrayList.size();
	}
	
	// Encontra a pocis�o no array 
	public Object getItem(int position) 
	{
		return ExercicioArrayList.get(position);
	}
	
	// Devolve a posi��o
	public long getItemId(int position) 
	{
		return position;
	}
	
	// Cria os elementos para receber o conteudo da tela
	static class ViewHolder {
		TextView txt_itemName;
	 	ImageView itemImage;
	
	 }
	
	// Vai converter um view para aparecer dentro de outro
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		if(convertView == null) // A lista ainda n�o foi criada
		{
			// cria o inflater que servira para converter para um xml ficar dentro do outro
			convertView = l_Inflater.inflate(R.layout.xml_exercicio, null);
			holder = new ViewHolder();
			// recebe o conteudo do xml que ser� um item da listView, associando os objtos da tela com os daqui
			holder.itemImage = (ImageView)convertView.findViewById(id.imgExercicioo);
			holder.txt_itemName = (TextView)convertView.findViewById(id.txtNomeExercicio);
			convertView.setTag(holder); // devolve os conteudos
		}
		else // se j� foi criada 
		{
			holder = (ViewHolder) convertView.getTag(); // Pega o conteudo que j� foi enviado
		}
		// 
		holder.txt_itemName.setText(ExercicioArrayList.get(position).getNome());
		holder.itemImage.setImageResource(imgId[ExercicioArrayList.get(position).getIdImage() - 1]);
		return convertView;
	}

}
