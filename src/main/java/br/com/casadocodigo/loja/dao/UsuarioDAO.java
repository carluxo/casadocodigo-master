package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("SELECT DISTINCT(u) FROM Usuario u LEFT JOIN FETCH u.roles WHERE u.email = :email", Usuario.class)
				.setParameter("email", email).getResultList();

		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}

		return usuarios.get(0);
	}

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

	public List<Usuario> listar() {
		return manager.createQuery("SELECT DISTINCT(u) FROM Usuario u LEFT JOIN FETCH u.roles", Usuario.class).getResultList();
	}

	public void atualizar(Usuario usuario) {
		manager.merge(usuario);
	}
}