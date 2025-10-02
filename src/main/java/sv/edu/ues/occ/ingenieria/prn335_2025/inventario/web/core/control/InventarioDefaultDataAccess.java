// src/main/java/sv/edu/ues/occ/ingenieria/prn335_2025/inventario/web/core/control/InventarioDefaultDataAccess.java
package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen;

import java.util.List;


public abstract class InventarioDefaultDataAccess<T> implements InventarioDAOInterface<T> {
    final Class<T> entityClass;

    public InventarioDefaultDataAccess(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    //probando git
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

    public List<T> findRange(int first, int max) throws IllegalArgumentException {
        if(first< 0 || max<1){
            throw new IllegalArgumentException("Los parametros first y max deben ser mayores que cero");
        }
        try{
            EntityManager em = getEntityManager();
            if(em != null){
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<T> cq = cb.createQuery(entityClass);
                Root<T> root = cq.from(entityClass);
                CriteriaQuery<T> all = cq.select(root);
                TypedQuery<T> allQuery = em.createQuery(all);
                allQuery.setFirstResult(first);
                allQuery.setMaxResults(max);
                return allQuery.getResultList();
            }
        }catch(Exception e){
            throw new IllegalStateException("Error al obtener el rango de registros", e);
        }
        throw new IllegalArgumentException("No se puede obtener los registros");
    }

    public int count() throws IllegalArgumentException {
        try{
            EntityManager em = getEntityManager();
            if(em != null) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Long> cq = cb.createQuery(Long.class);
                Root<T> root = cq.from(entityClass);
                CriteriaQuery<Long> all = cq.select(cb.count(root));
                TypedQuery<Long> allQuery = em.createQuery(all);
                return ((Long) allQuery.getSingleResult()).intValue();
            }
        }catch(Exception e){
            throw new IllegalStateException("Error al contar los registros", e);
        }
        return  -1;

    }

    public String carnet(){
        String carne = "MM23045";
        return carne;

    }
}