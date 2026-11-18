package com.banda.dao;

import com.banda.model.Banda;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BandaDAOJPA implements BandaDAO {

    private final EntityManager em;

    public BandaDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salvar(Banda banda) {
        em.getTransaction().begin();
        try {
            em.persist(banda);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Banda buscarPorId(Long id) {
        return em.find(Banda.class, id);
    }

    @Override
    public List<Banda> listarTodos() {
        return em.createQuery("SELECT b FROM Banda b", Banda.class)
                .getResultList();
    }

    @Override
    public void atualizar(Banda banda) {
        em.getTransaction().begin();
        try {
            em.merge(banda);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void remover(Long id) {
        em.getTransaction().begin();
        try {
            Banda banda = em.find(Banda.class, id);
            if (banda != null) {
                em.remove(banda);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Banda> listarPorGenero(String genero) {
        return em.createQuery("SELECT b FROM Banda b WHERE b.genero = :genero", Banda.class)
                .setParameter("genero", genero)
                .getResultList();
    }
}
