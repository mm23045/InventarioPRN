package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control;

import jakarta.persistence.EntityManager;

public abstract class InventarioDefaultDataAccess<T> implements InventarioDAOInterface<T> {
    protected EntityManager em;

    final Class<T> entityClass;

    public InventarioDefaultDataAccess(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public abstract EntityManager getEntityManager();

    public void crear(T registro) throws IllegalArgumentException {
        if (registro == null) {
            throw new IllegalArgumentException("El registro no puede ser nulo");
        }
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                entityManager.persist(registro);
            } else {
                throw new IllegalStateException("El EntityManager no ha sido inicializado");
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Error al almacenar el registro", ex);
        }
    }
}