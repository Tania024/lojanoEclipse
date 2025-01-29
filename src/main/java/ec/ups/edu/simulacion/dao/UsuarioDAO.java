package ec.ups.edu.simulacion.dao;

import java.util.List;

import ec.ups.edu.simulacion.modelo.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UsuarioDAO {
	@PersistenceContext
    private EntityManager em;

    public void create(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario read(String cedula) {
        return em.find(Usuario.class, cedula);
    }

    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    public void delete(String cedula) {
        Usuario usuario = read(cedula);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    public List<Usuario> findAll() {
        String jpql = "SELECT u FROM Usuario u";
        Query query = em.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

}
