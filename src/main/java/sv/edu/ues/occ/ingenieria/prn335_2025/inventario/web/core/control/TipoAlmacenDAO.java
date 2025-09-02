package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen;

import java.io.Serializable;

@Stateless
@LocalBean
public class TipoAlmacenDAO extends InventarioDefaultDataAccess<TipoAlmacen> implements Serializable {


    public TipoAlmacenDAO() {
        super(TipoAlmacen.class);
    }
    
    @PersistenceContext(unitName = "inventarioPU")
    EntityManager em;
    


    @Override
    public EntityManager getEntityManager() {
        return em;
    }


    @Override
    public void actualizar(TipoAlmacen registro) throws IllegalArgumentException {

    }

    @Override
    public void eliminar(Object id) throws IllegalArgumentException {

    }
}