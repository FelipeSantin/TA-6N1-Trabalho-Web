package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Cidades;
import br.edu.ifsul.modelo.UnidadesAtendimento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author Felipe
 */
@Named(value = "controleCidades")
@SessionScoped
public class ControleCidade implements Serializable {

    @EJB
    private CidadeDAO<Cidades> dao;
    private Cidades objeto;
    private Boolean editando;
    @EJB
    private PacienteDAO<UnidadesAtendimento> daoUnidadesAtendimento;
    private UnidadesAtendimento unidade;
    private Boolean editandoUnidade;
    private Boolean novaUnidade;

    public ControleCidade() {
        editando = false;
        editandoUnidade = false;
    }

    public String listar() {
        editando = false;
        return "/privado/cidade/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoUnidade = false;
        objeto = new Cidades();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoUnidade = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }

    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
            editandoUnidade = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }

    public void novaUnidade() {
        unidade = new UnidadesAtendimento();
        editandoUnidade = true;
        novaUnidade = true;
    }

    public void salvarUnidade() {
        if (unidade.getId() == null) {
            if (novaUnidade) {
                objeto.adicionarUnidadeAtendimento(unidade);
            }
        }
        editandoUnidade = false;
        Util.mensagemInformacao("Unidade persistido com sucesso!");
    }

    public void alterarUnidade(int index) {
        unidade = objeto.getUnidadesAtendimentos().get(index);
        editandoUnidade = true;
        novaUnidade = false;
    }

    public void excluirUnidade(int index) {
        objeto.removerUnidadeAtendimento(index);
        Util.mensagemInformacao("Unidade removida com sucesso!");
    }

    public Cidades getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidades objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public CidadeDAO<Cidades> getDao() {
        return dao;
    }

    public void setDao(CidadeDAO<Cidades> dao) {
        this.dao = dao;
    }

    public PacienteDAO<UnidadesAtendimento> getDaoUnidadesAtendimento() {
        return daoUnidadesAtendimento;
    }

    public void setDaoUnidadesAtendimento(PacienteDAO<UnidadesAtendimento> daoUnidadesAtendimento) {
        this.daoUnidadesAtendimento = daoUnidadesAtendimento;
    }

    public Boolean getEditandoUnidade() {
        return editandoUnidade;
    }

    public void setEditandoUnidade(Boolean editandoUnidade) {
        this.editandoUnidade = editandoUnidade;
    }

    public UnidadesAtendimento getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadesAtendimento unidade) {
        this.unidade = unidade;
    }

    public Boolean getNovoUnidade() {
        return novaUnidade;
    }

    public void setNovoUnidade(Boolean novaUnidade) {
        this.novaUnidade = novaUnidade;
    }
}
