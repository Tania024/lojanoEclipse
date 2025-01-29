package ec.ups.edu.simulacion.service;

import ec.ups.edu.simulacion.business.GestionUsuario;
import ec.ups.edu.simulacion.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("/usuarios")
public class UsuarioService {
	@Inject
    private GestionUsuario usuarioBusiness;

    // Crear un nuevo usuario
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario usuario) {
        try {
            usuarioBusiness.crearUsuario(usuario);
            Respuesta respuesta = new Respuesta(Respuesta.OK, "Usuario creado exitosamente.");
            return Response.status(Response.Status.CREATED).entity(respuesta).build();
        } catch (Exception e) {
            Respuesta respuesta = new Respuesta(Respuesta.ERROR, e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

    // Obtener usuario por cédula
    @GET
    @Path("/{cedula}")
    @Produces("application/json")
    public Response obtenerUsuario(@PathParam("cedula") String cedula) {
        try {
            Usuario usuario = usuarioBusiness.obtenerUsuario(cedula);
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (Exception e) {
            Respuesta respuesta = new Respuesta(Respuesta.ERROR, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
        }
    }

    // Actualizar usuario
    @PUT
    @Path("/{cedula}")
    @Produces("application/json")
    public Response actualizarUsuario(@PathParam("cedula") String cedula, Usuario usuario) {
        try {
            if (!usuario.getCedula().equals(cedula)) {
                throw new IllegalArgumentException("La cédula no coincide.");
            }
            usuarioBusiness.actualizarUsuario(usuario);
            Respuesta respuesta = new Respuesta(Respuesta.OK, "Usuario actualizado exitosamente.");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (Exception e) {
            Respuesta respuesta = new Respuesta(Respuesta.ERROR, e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

    // Eliminar usuario
    @DELETE
    @Path("/{cedula}")
    @Produces("application/json")
    public Response eliminarUsuario(@PathParam("cedula") String cedula) {
        try {
            usuarioBusiness.eliminarUsuario(cedula);
            Respuesta respuesta = new Respuesta(Respuesta.OK, "Usuario eliminado exitosamente.");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (Exception e) {
            Respuesta respuesta = new Respuesta(Respuesta.ERROR, e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

    // Listar todos los usuarios
    @GET
    @Produces("application/json")
    public Response listarUsuarios() {
        try {
            return Response.status(Response.Status.OK).entity(usuarioBusiness.listarUsuarios()).build();
        } catch (Exception e) {
            Respuesta respuesta = new Respuesta(Respuesta.ERROR, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

}
