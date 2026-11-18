package com.banda;

import com.banda.dao.BandaDAO;
import com.banda.dao.BandaDAOJPA;
import com.banda.model.Album;
import com.banda.model.Banda;
import com.banda.model.Integrante;
import com.banda.model.Musica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bandaPU");
        EntityManager em = emf.createEntityManager();

        try {
            BandaDAO bandaDAO = new BandaDAOJPA(em);

            Banda banda = new Banda("Legiao Urbana", "Rock");
            bandaDAO.salvar(banda);

            Integrante integrante1 = new Integrante("Renato Russo", "Vocal", banda);
            Integrante integrante2 = new Integrante("Dado Villa-Lobos", "Guitarra", banda);
            Album album = new Album("Dois", 1986, banda);
            Musica musica1 = new Musica("Tempo Perdido", 302, album);
            Musica musica2 = new Musica("Eduardo e Monica", 271, album);

            em.getTransaction().begin();
            em.persist(integrante1);
            em.persist(integrante2);
            em.persist(album);
            em.persist(musica1);
            em.persist(musica2);
            em.getTransaction().commit();

            Banda bandaExtra = new Banda("Os Paralamas", "MPB");
            bandaDAO.salvar(bandaExtra);
            bandaExtra.setGenero("Rock Nacional");
            bandaDAO.atualizar(bandaExtra);

            Banda encontrada = bandaDAO.buscarPorId(banda.getId());
            System.out.println("Encontrada por id: " + encontrada);

            List<Banda> todas = bandaDAO.listarTodos();
            System.out.println("Todas as bandas: " + todas);

            List<Banda> bandasRock = bandaDAO.listarPorGenero("Rock");
            System.out.println("Bandas de Rock: " + bandasRock);

            bandaDAO.remover(bandaExtra.getId());
            System.out.println("Apos remover banda extra: " + bandaDAO.listarTodos());
        } finally {
            em.close();
            emf.close();
        }
    }
}
