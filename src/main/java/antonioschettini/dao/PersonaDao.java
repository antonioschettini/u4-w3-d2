package antonioschettini.dao;

import antonioschettini.entities.Persona;
import antonioschettini.exceptions.NotFoundPersona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PersonaDao {
    private final EntityManager entityManager;

    public PersonaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Persona nuovaPersona) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(nuovaPersona);
            transaction.commit();
            System.out.println("La persona : " + nuovaPersona.getNome() + " " + nuovaPersona.getCognome() + " + stata salvata a db");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta della persona: " + e.getMessage());
        }
    }

    public Persona getById(long id) {
        Persona found = this.entityManager.find(Persona.class, id);
        if (found == null) {
            throw new NotFoundPersona("La persona con id: " + id + "non è stata trovata");
        }
        return found;
    }

}
