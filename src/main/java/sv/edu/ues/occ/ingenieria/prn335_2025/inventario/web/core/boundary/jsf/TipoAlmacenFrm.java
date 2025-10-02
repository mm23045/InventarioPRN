package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control.TipoAlmacenDAO;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TipoAlmacenFrm implements Serializable {

    protected String nombreBean = "TipoAlmacenFrm";
    @Inject
    TipoAlmacenDAO tADAO;

    List<TipoAlmacen> tipoAlmacenes;
    TipoAlmacen registro;

    @PostConstruct

    public void inicializar() {
        tipoAlmacenes = tADAO.findRange(0, 10);
    }

    public void btnNuevo(ActionEvent actionEvent) {
        this.registro = new TipoAlmacen();
    }
    public String getNombreBean() {
        return nombreBean;
    }
    public void setNombreBean(String nombreBean) {
        this.nombreBean = nombreBean;
    }

    public List<TipoAlmacen> getTipoAlmacenes() {
        return tipoAlmacenes;
    }

    public TipoAlmacen getRegistro() {
        return registro;
    }

    public void setRegistro(TipoAlmacen registro) {
        this.registro = registro;
    }
}
