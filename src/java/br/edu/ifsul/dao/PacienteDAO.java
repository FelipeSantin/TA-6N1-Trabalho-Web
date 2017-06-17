
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pacientes;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Felipe
 */
@Stateful
public class PacienteDAO<T> extends DAOGenerico<Pacientes> implements Serializable {

    public  PacienteDAO(){
        super();
        super.classePersistente = Pacientes.class;
    }
    
    @Override
    public Pacientes getObjectById(Integer id) throws Exception {
        Pacientes obj = (Pacientes) em.find(classePersistente, id);
        obj.getUnidadesPacientes().size();
        return obj;
    }    
    
    public Pacientes localizaPorNomePacientes(String usuario){
        Query query = em.createQuery("from Pacientes where upper(usuario) = :usuario");
        query.setParameter("usuario", usuario.toUpperCase());
        Pacientes obj = (Pacientes) query.getSingleResult();
        obj.getUnidadesPacientes().size();
        return obj;
    }
            
}
