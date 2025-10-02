package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.boundary.jsf;

import java.io.Serializable;

public class DefaultFRM<T> implements Serializable {
    public enum ESTADO_CRUD {
        NINGUNO, CREAR, ACTUALIZAR, ELIMINAR
    }

    ESTADO_CRUD estado = ESTADO_CRUD.NINGUNO;
}