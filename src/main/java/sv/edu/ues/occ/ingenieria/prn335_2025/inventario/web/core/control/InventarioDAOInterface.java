package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control;

import java.util.List;

public interface InventarioDAOInterface<T> {

    public void crear(T registro) throws IllegalArgumentException;

    public void actualizar(T registro) throws IllegalArgumentException;

    public void eliminar(Object id) throws IllegalArgumentException;

    public T buscarPorId(Object id) throws IllegalArgumentException;

    public List<T> findRange(int first, int max) throws IllegalArgumentException;

    public int count();


}
