package com.example.academia2.Core.Command.Impl;


import com.example.academia2.Core.Command.ICommand;
import com.example.academia2.Core.IFachada;
import com.example.academia2.Core.Impl.Controle.Fachada;

/**
 * Created by Galdino on 19/08/2015.
 */
public abstract class AbstractCommand implements ICommand {
    protected IFachada fachada = new Fachada();
}
