package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "almacen")
public class Almacen {
    @Id
    @Column(name = "id_almacen", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_almacen")
    private sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen idTipoAlmacen;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen getIdTipoAlmacen() {
        return idTipoAlmacen;
    }

    public void setIdTipoAlmacen(sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen idTipoAlmacen) {
        this.idTipoAlmacen = idTipoAlmacen;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}