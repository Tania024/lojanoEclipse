package ec.ups.edu.simulacion.business;

import java.util.List;

import ec.ups.edu.simulacion.dao.UsuarioDAO;
import ec.ups.edu.simulacion.modelo.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Inicio {
	@Inject
    private UsuarioDAO daoUsuario;
    
    @PostConstruct
    public void init() {
        System.out.println("Inicializando datos...");

        Usuario u = new Usuario();
        u.setCedula("0106717671");
        u.setNombre("Tania Lojano");
        u.setConsumo(11);
        u.setDeuda(1);
        daoUsuario.create(u);
      
        
        List<Usuario> listadoClientes = daoUsuario.findAll();
        for (Usuario us : listadoClientes) {
            System.out.println(us.getCedula() + " " + us.getNombre() + " " + us.getConsumo() + " " + us.getDeuda());
        }
    }  

}
