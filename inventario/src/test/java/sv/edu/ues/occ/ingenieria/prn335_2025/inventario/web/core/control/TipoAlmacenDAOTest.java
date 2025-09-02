package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen;

import static org.junit.jupiter.api.Assertions.*;

class TipoAlmacenDAOTest {

    @Test
    void crear() {
        TipoAlmacen nuevo = new TipoAlmacen();
        nuevo.setNombre("Tipo Almacen 1");
        nuevo.setActivo(true);

        EntityManager mockEm = Mockito.mock(EntityManager.class);

        TipoAlmacenDAO cut = new TipoAlmacenDAO();
        cut.em = mockEm; // Asignar el mock antes de llamar a crear

        cut.crear(nuevo);

        Mockito.verify(mockEm).persist(nuevo);
    }
}