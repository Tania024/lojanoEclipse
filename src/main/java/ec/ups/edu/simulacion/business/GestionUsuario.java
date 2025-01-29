package ec.ups.edu.simulacion.business;

import java.util.List;

import ec.ups.edu.simulacion.dao.UsuarioDAO;
import ec.ups.edu.simulacion.modelo.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class GestionUsuario {
	@Inject
    private UsuarioDAO usuarioDAO;

    // Crear un nuevo usuario
    public void crearUsuario(Usuario usuario) throws Exception {
        if (usuario == null || usuario.getCedula() == null || usuario.getCedula().isEmpty()) {
            throw new Exception("El usuario o su cédula no son válidos.");
        }
        try {
            usuarioDAO.create(usuario);
        } catch (Exception e) {
            throw new Exception("Error al crear el usuario: " + e.getMessage());
        }
    }

    // Obtener usuario por cédula
    public Usuario obtenerUsuario(String cedula) throws Exception {
        if (cedula == null || cedula.isEmpty()) {
            throw new Exception("La cédula no es válida.");
        }
        Usuario usuario = usuarioDAO.read(cedula);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado.");
        }
        return usuario;
    }

    // Actualizar usuario
    public void actualizarUsuario(Usuario usuario) throws Exception {
        if (usuario == null || usuario.getCedula() == null || usuario.getCedula().isEmpty()) {
            throw new Exception("El usuario o su cédula no son válidos.");
        }
        Usuario existente = usuarioDAO.read(usuario.getCedula());
        if (existente == null) {
            throw new Exception("El usuario no existe.");
        }
        try {
            usuarioDAO.update(usuario);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Eliminar usuario
    public void eliminarUsuario(String cedula) throws Exception {
        if (cedula == null || cedula.isEmpty()) {
            throw new Exception("La cédula no es válida.");
        }
        Usuario usuario = usuarioDAO.read(cedula);
        if (usuario == null) {
            throw new Exception("El usuario no existe.");
        }
        try {
            usuarioDAO.delete(cedula);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() throws Exception {
        try {
            return usuarioDAO.findAll();
        } catch (Exception e) {
            throw new Exception("Error al listar los usuarios: " + e.getMessage());
        }
    }

}
