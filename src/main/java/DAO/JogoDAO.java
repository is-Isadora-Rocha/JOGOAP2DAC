package DAO;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.*;
import util.JPAUtil;

public class JogoDAO {
	
	public static void salvar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void editar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(jogo);
		em.getTransaction().commit();
		em.close();
	}

	public static void deletar(int id) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Jogo jogoDeleted = em.find(Jogo.class, id);
		
		if(jogoDeleted != null) {
			em.remove(jogoDeleted);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Jogo> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		
		Query q = em.createNamedQuery("Jogo.listar");
		List<Jogo> lista = q.getResultList();
		em.clear();
		return lista;
	}
	
 	

 public static Integer encontrarMaximoNumeroSorteado() {
     EntityManager entityManager = JPAUtil.criarEntityManager();
     TypedQuery<Integer> query = entityManager.createNamedQuery("Jogo.findMaxNumeroSorteado", Integer.class);
     Integer maximoNumeroSorteado = query.getSingleResult();
     entityManager.clear();
     return maximoNumeroSorteado;
 }

 public static Integer encontrarMaximoNumeroDasVariaveis(Jogo jogo) {
     List<Integer> valores = Arrays.asList(jogo.getV1(), jogo.getV2(), jogo.getV3(), jogo.getV4(), jogo.getV5());
     return valores.stream().max(Integer::compareTo).orElse(null);
 }

}
