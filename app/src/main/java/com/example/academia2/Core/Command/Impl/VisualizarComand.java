package com.example.academia2.Core.Command.Impl;


import com.example.academia2.Core.Aplicacao.Resultado;
import com.example.academia2.Dominio.EntidadeDominio;

/**
 * Created by Galdino on 20/08/2015.
 */
public class VisualizarComand extends AbstractComand {
    public Resultado execute(EntidadeDominio entidade) {

        return fachada.visualizar(entidade);
    }
}
