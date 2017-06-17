
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.UnidadesAtendimento;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Felipe
 */
@Stateful
public class UnidadeDAO<T> extends DAOGenerico<UnidadesAtendimento> implements Serializable {

    public  UnidadeDAO(){
        super();
        super.classePersistente = UnidadesAtendimento.class;
    }
            
}
