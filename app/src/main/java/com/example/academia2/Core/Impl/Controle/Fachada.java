package com.example.academia2.Core.Impl.Controle;


import com.example.academia2.Core.Aplicacao.Resultado;
import com.example.academia2.Core.IDAO;
import com.example.academia2.Core.IFachada;
import com.example.academia2.Core.IStrategy;
import com.example.academia2.Core.Impl.SqlDAO.SQLexercicio;
import com.example.academia2.Core.Impl.SqlDAO.SQLgrupo;
import com.example.academia2.Core.Impl.SqlDAO.SQLtreino;
import com.example.academia2.Dominio.EntidadeDominio;
import com.example.academia2.Dominio.Exercicio;
import com.example.academia2.Dominio.GrupoMuscular;
import com.example.academia2.Dominio.Treino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Galdino on 20/08/2015.
 */
public class Fachada  implements IFachada {
    private Map<String, IDAO> daos;
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Resultado resultado;
    private Session session;

    public Fachada()
    {
        session = Session.getInstance();
        daos = new HashMap<String, IDAO>();
        rns = new HashMap<String, Map<String, List<IStrategy>>>();

        // Servidor
        if(session.getContext() == null) {
            /************** Criando instancias dos DAOs a serem utilizados************************/
//            LuzDAO luzDAO = new LuzDAO();
//            AguaDAO aguaDAO = new AguaDAO();
//            ResidenciaServerDAO residenciaServerDAO = new ResidenciaServerDAO();
//            GastoHojeDAO gastoHojeDAO = new GastoHojeDAO();
//            GastoAtualDAO gastoAtualDAO = new GastoAtualDAO();
//            GastoMesDAO gastoMesDAO = new GastoMesDAO();
//            GastoHoraDAO gastoHoraDAO = new GastoHoraDAO();
//            /***************ADD AS CLASSES DAO CORRESPONDENTES AS CLASSES CONCRETAS ******/
//            daos.put(Luz.class.getName(), luzDAO);
//            daos.put(Agua.class.getName(), aguaDAO);
//            daos.put(Residencia.class.getName(), residenciaServerDAO);
//            daos.put(GastoHoje.class.getName(),gastoHojeDAO);
//            daos.put(GastoAtual.class.getName(), gastoAtualDAO);
//            daos.put(GastoMes.class.getName(), gastoMesDAO);
//            daos.put(GastoHora.class.getName(), gastoHoraDAO);
        }
        else// Banco interno
        {
            /***************ADD AS CLASSES DAO CORRESPONDENTES AS CLASSES CONCRETAS ******/
            if(session.getNameInstanceClass() != null) {
//                if (session.getNameInstanceClass() == Residencia.class.getName()) {
//                    ResidenciaSqlDAO residenciaSqlDAO = new ResidenciaSqlDAO(session.getContext());
//                    daos.put(Residencia.class.getName(), residenciaSqlDAO);
//                }
                if (session.getNameInstanceClass() == Exercicio.class.getName()) {
                    SQLexercicio sqlExercicio = new SQLexercicio(session.getContext());
                    daos.put(Exercicio.class.getName(), sqlExercicio);
                }
                if (session.getNameInstanceClass() == GrupoMuscular.class.getName()) {
                    SQLgrupo sqlGrupo = new SQLgrupo(session.getContext());
                    daos.put(GrupoMuscular.class.getName(), sqlGrupo);
                }
                if (session.getNameInstanceClass() == Treino.class.getName()) {
                    SQLtreino sqlTreino = new SQLtreino(session.getContext());
                    daos.put(Treino.class.getName(), sqlTreino);
                }
                // ADD AS CLASSES DAO CORRESPONDENTES AS CLASSES CONCRETAS ******
            }
        }



        // Criando instancias de regras de negocio a serem utilizados
//        ValidarDadosObrigatoriosTroca rValidarDadosObrigatoriosTroca = new ValidarDadosObrigatoriosTroca();
//        ComplementarDtCadastro rComplementarDtCadastro = new ComplementarDtCadastro();
//        ValidarDadosInclProduto rValidarDadosInclProduto = new ValidarDadosInclProduto();
        //

        // ADD as classes que ser�o testadas
//        List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
//        List<IStrategy> rnsSalvarVenda = new ArrayList<IStrategy>();
//        List<IStrategy> rnsSalvarProdTrocado = new ArrayList<IStrategy>();
//        List<IStrategy> rnsSalvarTroca = new ArrayList<IStrategy>();
//
        // ****** N�O ADD LISTA DE REGRAS PARA CLASSE ProdVendido

        // Adiciona as regras do negocio que seram testadas
        //rnsSalvarProdTrocado.add(rValidarVendaPromocao);
//        rnsSalvarTroca.add(rValidarDadosObrigatoriosTroca);
//        rnsSalvarTroca.add(rValidarDiasTroca);
//        rnsSalvarVenda.add(rComplementarDtCadastro);
//
        /*
        * Cria o mapa que poder? conter todas as listas de regras de neg?cio espec?fica
        */
        // Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
//        Map<String, List<IStrategy>> rnsProdTrocado = new HashMap<String, List<IStrategy>>();
//        Map<String, List<IStrategy>> rnsVenda = new HashMap<String, List<IStrategy>>();
//        Map<String, List<IStrategy>> rnsTroca = new HashMap<String, List<IStrategy>>();
//
        /*
        * Adiciona a listra de regras na opera??o salvar no mapa do fornecedor
        */
        // colocar quando quer que a regra seja testada, se ao SALVAR, ou ALTERAR, EXCLUIR, PESQUISAR
        // rnsProduto.put("SALVAR", rnsSalvarProduto);
//        rnsProdTrocado.put("salvar",rnsSalvarProdTrocado);
//        rnsVenda.put("salvar", rnsSalvarVenda);
//        rnsTroca.put("salvar", rnsSalvarTroca);

        // devolve o resultado das regras
//        rns.put(Venda.class.getName(), rnsVenda);
//        rns.put(Produto1.class.getName(), rnsProduto);
//        rns.put(Fornecedor.class.getName(), rnsFornecedor);

    }

    /*
     * (non-Javadoc)
     * @see les12015.core.IFachada#salvar(les12015.dominio.EntidadeDominio)
     * map <string, list<Istrategy>
     * map<String, map<String, List<Strategy>>)
     *
     */


    public Resultado salvar(EntidadeDominio entidade)
    {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();
        String msg = executarRegras(entidade, "salvar");

        if(msg == null)
        {
            IDAO dao = daos.get(nmClasse);
            try
            {
                dao.salvar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            }
            // MUDEI *****
            catch (Exception e)
            {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar o registro!");
            }
        }
        else
        {
            resultado.setMsg(msg);
        }
        return resultado;
    }


    public Resultado alterar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "alterar");

        if(msg == null)
        {
            IDAO dao = daos.get(nmClasse);
            try
            {
                dao.alterar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            }
            // MUDEI *****
            catch (Exception e)
            {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar o registro!");

            }
        }
        else
        {
            resultado.setMsg(msg);
        }

        return resultado;

    }


    public Resultado excluir(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "excluir");

        if(msg == null)
        {
            IDAO dao = daos.get(nmClasse);
            try
            {
                dao.excluir(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            }
            // MUDEI *****
            catch (Exception e)
            {
                e.printStackTrace();
                resultado.setMsg("Nãoo foi possível realizar o registro!");

            }
        }else
        {
            resultado.setMsg(msg);
        }

        return resultado;

    }


    public Resultado consultar(EntidadeDominio entidade)
    {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "consultar");

        if(msg == null)
        {
            IDAO dao = daos.get(nmClasse);
            try
            {
                resultado.setEntidades(dao.consultar(entidade));
            }
            // MUDEI *****
            catch (Exception e)
            {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a consulta!");
            }
        }
        else
        {
            resultado.setMsg(msg);
        }

        return resultado;
    }

    public Resultado visualizar(EntidadeDominio entidade) {
        resultado = new Resultado();
        resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
        resultado.getEntidades().add(entidade);
        return resultado;

    }

    private String executarRegras(EntidadeDominio entidade, String operacao){
        String nmClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
        if(regrasOperacao != null)
        {
            List<IStrategy> regras = regrasOperacao.get(operacao);

            if(regras != null)
            {
                for(IStrategy s: regras)
                {
                    String m = s.processar(entidade);

                    if(m != null)
                    {
                        msg.append(m);
                        msg.append("\n");
                    }
                }
            }

        }

        if(msg.length()>0)
            return msg.toString();
        else
            return null;
    }
}
