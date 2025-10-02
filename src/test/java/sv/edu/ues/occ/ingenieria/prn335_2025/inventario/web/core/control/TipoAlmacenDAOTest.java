package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

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

    @BeforeEach
    void init() {
        this.listaTipoAlmacen = Arrays.asList(new TipoAlmacen(), new TipoAlmacen());
    }

    // ---------- Tests para crear() ----------
    @Test
    void crear_ok_usaPersist() {
        EntityManager mockEm = mock(EntityManager.class);
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        TipoAlmacen entidad = new TipoAlmacen();
        cut.crear(entidad);

        verify(mockEm, times(1)).persist(entidad);
    }

    @Test
    void crear_registroNull_lanzaIllegalArgument() {
        EntityManager mockEm = mock(EntityManager.class);
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalArgumentException.class, () -> cut.crear(null));
    }

    @Test
    void crear_entityManagerNull_lanzaIllegalState() {
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return null; }
        };

        assertThrows(IllegalStateException.class, () -> cut.crear(new TipoAlmacen()));
    }

    @Test
    void crear_persistFalla_lanzaIllegalState() {
        EntityManager mockEm = mock(EntityManager.class);
        doThrow(new RuntimeException("fail")).when(mockEm).persist(any());

        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalStateException.class, () -> cut.crear(new TipoAlmacen()));
    }

    // ---------- Tests para buscarPorId() ----------
    @Test
    void buscarPorId_ok_devuelveEntidad() {
        EntityManager mockEm = mock(EntityManager.class);
        TipoAlmacen esperado = new TipoAlmacen();
        when(mockEm.find(TipoAlmacen.class, 1L)).thenReturn(esperado);

        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        TipoAlmacen res = cut.buscarPorId(1L);
        assertSame(esperado, res);
        verify(mockEm, times(1)).find(TipoAlmacen.class, 1L);
    }

    @Test
    void buscarPorId_idNull_lanzaIllegalArgument() {
        EntityManager mockEm = mock(EntityManager.class);
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalArgumentException.class, () -> cut.buscarPorId(null));
    }

    @Test
    void buscarPorId_entityManagerNull_lanzaIllegalState() {
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return null; }
        };

        assertThrows(IllegalStateException.class, () -> cut.buscarPorId(1L));
    }

    @Test
    void buscarPorId_findFalla_lanzaIllegalState() {
        EntityManager mockEm = mock(EntityManager.class);
        when(mockEm.find(TipoAlmacen.class, 99L)).thenThrow(new RuntimeException("fail"));

        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalStateException.class, () -> cut.buscarPorId(99L));
    }

    // ---------- Tests para actualizar() ----------
    @Test
    void actualizar_ok_usaMerge() {
        EntityManager mockEm = mock(EntityManager.class);
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        TipoAlmacen entidad = new TipoAlmacen();
        cut.actualizar(entidad);

        verify(mockEm, times(1)).merge(entidad);
    }

    @Test
    void actualizar_registroNull_lanzaIllegalArgument() {
        EntityManager mockEm = mock(EntityManager.class);
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalArgumentException.class, () -> cut.actualizar(null));
    }

    @Test
    void actualizar_entityManagerNull_lanzaIllegalState() {
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return null; }
        };

        assertThrows(IllegalStateException.class, () -> cut.actualizar(new TipoAlmacen()));
    }

    @Test
    void actualizar_mergeFalla_lanzaIllegalState() {
        EntityManager mockEm = mock(EntityManager.class);
        when(mockEm.merge(any())).thenThrow(new RuntimeException("fail"));

        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalStateException.class, () -> cut.actualizar(new TipoAlmacen()));
    }

    // ---------- Tests para eliminar() ----------
    @Test
    void eliminar_ok_encuentraYElimina() {
        EntityManager mockEm = mock(EntityManager.class);
        TipoAlmacen entidad = new TipoAlmacen();
        when(mockEm.find(TipoAlmacen.class, 1L)).thenReturn(entidad);

        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        cut.eliminar(1L);
        verify(mockEm, times(1)).remove(entidad);
    }

    @Test
    void eliminar_idNull_lanzaIllegalArgument() {
        EntityManager mockEm = mock(EntityManager.class);
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalArgumentException.class, () -> cut.eliminar(null));
    }

    @Test
    void eliminar_entityManagerNull_lanzaIllegalState() {
        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return null; }
        };

        assertThrows(IllegalStateException.class, () -> cut.eliminar(1L));
    }

    @Test
    void eliminar_noEncontrado_lanzaIllegalState() {
        EntityManager mockEm = mock(EntityManager.class);
        when(mockEm.find(TipoAlmacen.class, 2L)).thenReturn(null);

        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalStateException.class, () -> cut.eliminar(2L));
    }

    @Test
    void eliminar_removeFalla_lanzaIllegalState() {
        EntityManager mockEm = mock(EntityManager.class);
        TipoAlmacen entidad = new TipoAlmacen();
        when(mockEm.find(TipoAlmacen.class, 3L)).thenReturn(entidad);
        doThrow(new RuntimeException("fail")).when(mockEm).remove(entidad);

        InventarioDefaultDataAccess<TipoAlmacen> cut = new InventarioDefaultDataAccess<>(TipoAlmacen.class) {
            @Override public EntityManager getEntityManager() { return mockEm; }
        };

        assertThrows(IllegalStateException.class, () -> cut.eliminar(3L));
    }

    // ---------- Tests para findRange() ----------
    @Test
    void findRange_parametrosInvalidos_lanzaIllegalArgument() {
        EntityManager mockEm = mock(EntityManager.class);
        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = mockEm;

        assertThrows(IllegalArgumentException.class, () -> cut.findRange(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> cut.findRange(0, 0));
    }

    @Test
    void findRange_ok_devuelveLista() {
        EntityManager mockEm = mock(EntityManager.class);
        CriteriaBuilder mockCb = mock(CriteriaBuilder.class);
        CriteriaQuery<TipoAlmacen> mockCq = mock(CriteriaQuery.class);
        Root<TipoAlmacen> mockRoot = mock(Root.class);
        TypedQuery<TipoAlmacen> mockTq = mock(TypedQuery.class);

        when(mockEm.getCriteriaBuilder()).thenReturn(mockCb);
        when(mockCb.createQuery(TipoAlmacen.class)).thenReturn(mockCq);
        when(mockCq.from(TipoAlmacen.class)).thenReturn(mockRoot);
        when(mockCq.select(mockRoot)).thenReturn(mockCq);
        when(mockEm.createQuery(mockCq)).thenReturn(mockTq);
        when(mockTq.getResultList()).thenReturn(this.listaTipoAlmacen);

        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = mockEm;

        List<TipoAlmacen> result = cut.findRange(0, 10);
        assertEquals(this.listaTipoAlmacen, result);
        verify(mockTq).setFirstResult(0);
        verify(mockTq).setMaxResults(10);
    }

    @Test
    void findRange_entityManagerNull_lanzaIllegalArgument() {
        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = null;

        assertThrows(IllegalArgumentException.class, () -> cut.findRange(0, 5));
    }

    @Test
    void findRange_excepcionEnBuilder_lanzaIllegalState() {
        EntityManager mockEm = mock(EntityManager.class);
        when(mockEm.getCriteriaBuilder()).thenThrow(new RuntimeException("fail"));

        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = mockEm;

        assertThrows(IllegalStateException.class, () -> cut.findRange(0, 5));
    }

    // ---------- Tests para count() ----------
    @Test
    void count_ok_devuelveEntero() {
        EntityManager mockEm = mock(EntityManager.class);
        CriteriaBuilder mockCb = mock(CriteriaBuilder.class);
        CriteriaQuery<Long> mockCqLong = mock(CriteriaQuery.class);
        Root<TipoAlmacen> mockRoot = mock(Root.class);
        @SuppressWarnings("unchecked")
        Expression<Long> mockExpr = (Expression<Long>) mock(Expression.class);
        TypedQuery<Long> mockTqLong = mock(TypedQuery.class);

        when(mockEm.getCriteriaBuilder()).thenReturn(mockCb);
        when(mockCb.createQuery(Long.class)).thenReturn(mockCqLong);
        when(mockCqLong.from(TipoAlmacen.class)).thenReturn(mockRoot);
        when(mockCb.count(mockRoot)).thenReturn(mockExpr);
        when(mockCqLong.select(mockExpr)).thenReturn(mockCqLong);
        when(mockEm.createQuery(mockCqLong)).thenReturn(mockTqLong);
        when(mockTqLong.getSingleResult()).thenReturn(7L);

        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = mockEm;

        int result = cut.count();
        assertEquals(7, result);
    }

    @Test
    void count_entityManagerNull_retornaMenosUno() {
        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = null;

        int result = cut.count();
        assertEquals(-1, result);
    }

    @Test
    void count_excepcionEnBuilder_lanzaIllegalState() {
        EntityManager mockEm = mock(EntityManager.class);
        when(mockEm.getCriteriaBuilder()).thenThrow(new RuntimeException("fail"));

        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = mockEm;

        assertThrows(IllegalStateException.class, cut::count);
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
    @Test
    void carnet() {
        System.out.println("============================");
        System.out.println("Examen para el alumno");
        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        System.out.println(cut.carnet());
    }
}