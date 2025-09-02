// src/main/java/sv/edu/ues/occ/ingenieria/prn335_2025/inventario/web/core/control/InventarioDefaultDataAccess.java
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

    public T buscarPorId(Object id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                return entityManager.find(entityClass, id);
            } else {
                throw new IllegalStateException("El EntityManager no ha sido inicializado");
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Error al buscar el registro", ex);
        }
    }

    public void actualizar(T registro) throws IllegalArgumentException {
        if (registro == null) {
            throw new IllegalArgumentException("El registro no puede ser nulo");
        }
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                entityManager.merge(registro);
            } else {
                throw new IllegalStateException("El EntityManager no ha sido inicializado");
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Error al actualizar el registro", ex);
        }
    }

    public void eliminar(Object id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                T entidad = entityManager.find(entityClass, id);
                if (entidad != null) {
                    entityManager.remove(entidad);
                } else {
                    throw new IllegalArgumentException("No se encontró el registro a eliminar");
                }
            } else {
                throw new IllegalStateException("El EntityManager no ha sido inicializado");
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Error al eliminar el registro", ex);
        }
    }
}