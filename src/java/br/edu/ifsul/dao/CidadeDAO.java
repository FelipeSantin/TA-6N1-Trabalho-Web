package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cidades;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Felipe
 */
@Stateful
public class CidadeDAO<T> extends DAOGenerico<Cidades> implements Serializable {

    public CidadeDAO() {
        super();
        super.setClassePersistente(Cidades.class);
        super.setOrdem("nome");        
    }
 
    @Override
    public Cidades getObjectById(Integer id) throws Exception {
        Cidades obj = (Cidades) super.getEm().find(super.getClassePersistente(), id);
        obj.getUnidadesAtendimentos().size();
        return obj;
    }       
    
}
