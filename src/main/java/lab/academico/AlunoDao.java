package lab.academico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Data Access Object.
 */
public class AlunoDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab-persistence-unit");

	public static void inclui(String matricula, String nome) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Aluno aluno = new Aluno();
		aluno.setMatricula(matricula);
		aluno.setNome(nome);

		// Grava o objeto no banco de dados.
		em.persist(aluno);
		em.getTransaction().commit();
		em.close();
	}

	public static void alterar(String matricula, String nome) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Aluno aluno = new Aluno();
		aluno.setMatricula(matricula);
		aluno.setNome(nome);
		
		em.merge(aluno);
		em.getTransaction().commit();

	}

	public static void excluir(String matricula) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		Aluno aluno = em.find(Aluno.class, matricula);

		em.getTransaction().begin();
		em.remove(aluno);
		em.getTransaction().commit();
	}

	public static List<Aluno> listar() {
		EntityManager em = emf.createEntityManager();
		String jpql = "from Aluno";
		TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
		List<Aluno> result = query.getResultList();
		em.close();
		return result;
	}
}
