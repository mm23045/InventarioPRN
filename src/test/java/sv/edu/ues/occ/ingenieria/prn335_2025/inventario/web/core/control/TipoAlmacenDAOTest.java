package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TipoAlmacenDAOTest {

    private List<TipoAlmacen> listaTipoAlmacen;

    @Test
    void crear() {
        TipoAlmacen nuevo = new TipoAlmacen();
        nuevo.setNombre("Tipo Almacen 1");
        nuevo.setActivo(true);

        EntityManager mockEm = Mockito.mock(EntityManager.class);

        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = mockEm; // Asignar el mock antes de llamar a crear

        cut.crear(nuevo);
    }

    @Test
    public void findRange() {
        System.out.println("findRange");
        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        EntityManager mockEm = Mockito.mock(EntityManager.class);

        CriteriaBuilder mockCb = Mockito.mock(CriteriaBuilder.class);
        Mockito.when(mockEm.getCriteriaBuilder()).thenReturn(mockCb);

        CriteriaQuery<TipoAlmacen> mockCq = Mockito.mock(CriteriaQuery.class);
        Mockito.when(mockCb.createQuery(TipoAlmacen.class)).thenReturn(mockCq);

        Root<TipoAlmacen> mockRoot = Mockito.mock(Root.class);
        Mockito.when(mockCq.from(TipoAlmacen.class)).thenReturn(mockRoot);

        CriteriaQuery<TipoAlmacen> mockCriteriaQuery = Mockito.mock(CriteriaQuery.class);
        Mockito.when(mockCq.select(mockRoot)).thenReturn(mockCriteriaQuery);

        TypedQuery<TipoAlmacen> mockTypedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(mockEm.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        Mockito.when(mockTypedQuery.getResultList()).thenReturn(this.listaTipoAlmacen);

        cut.em = mockEm;

        assertThrows(IllegalArgumentException.class, () -> cut.findRange(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> cut.findRange(1, -1));

        List<TipoAlmacen> result = cut.findRange(0, 10);
        assertEquals(this.listaTipoAlmacen, result);
    }
}