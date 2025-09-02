package sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.boundary;


import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.control.TipoAlmacenDAO;
import sv.edu.ues.occ.ingenieria.prn335_2025.inventario.web.core.entity.TipoAlmacen;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TipoAlmacenServlet", urlPatterns = "web/tipo_almacen")
public class TipoAlmacenServlet extends HttpServlet {

    @Inject
    TipoAlmacenDAO taDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String nombre = req.getParameter("nombre");
        if (nombre == null) {
            sb.append("El nombre es obligatorio");
        }else {
            if (nombre!=null){
                TipoAlmacen tipoAlmacen = new TipoAlmacen();
                tipoAlmacen.setNombre(nombre);
                tipoAlmacen.setActivo(true);

            }
        }
        try {
            PrintWriter writer = resp.getWriter();

        }catch (Exception ex){

        }
    }
}
