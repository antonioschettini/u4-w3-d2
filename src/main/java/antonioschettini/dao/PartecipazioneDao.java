package antonioschettini.dao;

import antonioschettini.entities.Partecipazione;
import antonioschettini.exceptions.NotFoundPartecipazione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PartecipazioneDao {
    private final EntityManager entityManager;

    public PartecipazioneDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Partecipazione nuovaPartecipazione) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        try {
            transaction.begin();
            this.entityManager.persist(nuovaPartecipazione);
            transaction.commit();
            System.out.println("La partecipazione con id: " + nuovaPartecipazione.getId() + " è stata aggiunta a db");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta della partecipazione " + e.getMessage());
        }
    }

    public Partecipazione getById(long id) {
        Partecipazione found = this.entityManager.find(Partecipazione.class, id);
        if (found == null) {
            throw new NotFoundPartecipazione("La partecipazione con id:" + id + " non è stata trovata");
        }
        return found;
    }
}

