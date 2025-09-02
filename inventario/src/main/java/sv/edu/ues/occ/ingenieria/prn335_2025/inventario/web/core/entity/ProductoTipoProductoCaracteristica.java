package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "producto_tipo_producto_caracteristica")
public class ProductoTipoProductoCaracteristica {
    @Id
    @Column(name = "id_producto_tipo_producto_caracteristica", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto_tipo_producto")
    private ProductoTipoProducto idProductoTipoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_producto_caracteristica")
    private sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoProductoCaracteristica idTipoProductoCaracteristica;

    @Lob
    @Column(name = "valor")
    private String valor;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductoTipoProducto getIdProductoTipoProducto() {
        return idProductoTipoProducto;
    }

    public void setIdProductoTipoProducto(ProductoTipoProducto idProductoTipoProducto) {
        this.idProductoTipoProducto = idProductoTipoProducto;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoProductoCaracteristica getIdTipoProductoCaracteristica() {
        return idTipoProductoCaracteristica;
    }

    public void setIdTipoProductoCaracteristica(sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoProductoCaracteristica idTipoProductoCaracteristica) {
        this.idTipoProductoCaracteristica = idTipoProductoCaracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}