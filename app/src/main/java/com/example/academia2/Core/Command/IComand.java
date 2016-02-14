package com.example.academia2.Core.Command;

import com.example.academia2.Core.Aplicacao.Resultado;
import com.example.academia2.Dominio.EntidadeDominio;

/**
 * Created by Galdino on 14/02/2016.
 */
public interface IComand {
    public Resultado execute(EntidadeDominio entidade);
}

