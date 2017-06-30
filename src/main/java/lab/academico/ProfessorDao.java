package lab.academico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Data Access Object.
 */
public class ProfessorDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab-persistence-unit");

	public static void inclui(String matricula, String nome) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Professor professor = new Professor();
		professor.setMatricula(matricula);
		professor.setNome(nome);

		// Grava o objeto no banco de dados.
		em.persist(professor);
		em.getTransaction().commit();
		em.close();
	}

	public static void alterar(String matricula, String nome) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Professor professor = new Professor();
		professor.setMatricula(matricula);
		professor.setNome(nome);
		
		em.merge(professor);
		em.getTransaction().commit();

	}

	public static void excluir(String matricula) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		Professor professor = em.find(Professor.class, matricula);

		em.getTransaction().begin();
		em.remove(professor);
		em.getTransaction().commit();
	}

	public static List<Professor> listar() {
		EntityManager em = emf.createEntityManager();
		String jpql = "from Professor";
		TypedQuery<Professor> query = em.createQuery(jpql, Professor.class);
		List<Professor> result = query.getResultList();
		em.close();
		return result;
	}
}
