package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.UnidadeDAO;
import br.edu.ifsul.modelo.Pacientes;
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
@Named(value = "controlePacientes")
@SessionScoped
public class ControlePaciente implements Serializable {
    
    @EJB
    private PacienteDAO<Pacientes> dao;
    private Pacientes objeto;
    private Boolean editando;
    @EJB
    private UnidadeDAO<UnidadesAtendimento> daoUnidade;
    private UnidadesAtendimento unidade;
    private Boolean editandoUnidade;
    
    public ControlePaciente() {
        editando = false;
        editandoUnidade = false;
    }
    
    public String listar() {
        editando = false;
        return "/privado/paciente/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Pacientes();
        editando = true;
        editandoUnidade = false;
    }
    
    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoUnidade = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void novaUnidade() {
        editandoUnidade = true;
    }
    
    public void salvarUnidade() {
        if (!objeto.getUnidadesPacientes().contains(unidade)) {
            objeto.getUnidadesPacientes().add(unidade);
            Util.mensagemInformacao("Unidade adicionada com sucesso!");
        } else {
            Util.mensagemErro("Usuário já possui esta unidade!");
        }
        editandoUnidade = false;
    }
    
    public void removerUnidade(UnidadesAtendimento obj) {
        objeto.getUnidadesPacientes().remove(obj);
        Util.mensagemInformacao("Unidade removida com sucesso!");
    }
    
    public PacienteDAO<Pacientes> getDao() {
        return dao;
    }
    
    public void setDao(PacienteDAO<Pacientes> dao) {
        this.dao = dao;
    }
    
    public Pacientes getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Pacientes objeto) {
        this.objeto = objeto;
    }
    
    public Boolean getEditando() {
        return editando;
    }
    
    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    public UnidadeDAO<UnidadesAtendimento> getDaoUnidade() {
        return daoUnidade;
    }
    
    public void setDaoUnidade(UnidadeDAO<UnidadesAtendimento>  daoUnidade) {
        this.daoUnidade = daoUnidade;
    }
    
    public UnidadesAtendimento getUnidade() {
        return unidade;
    }
    
    public void setUnidade(UnidadesAtendimento unidade) {
        this.unidade = unidade;
    }
    
    public Boolean getEditandoUnidade() {
        return editandoUnidade;
    }
    
    public void setEditandoUnidade(Boolean editandoUnidade) {
        this.editandoUnidade = editandoUnidade;
    }
}
