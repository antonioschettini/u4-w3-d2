package antonioschettini.dao;

import antonioschettini.entities.Location;
import antonioschettini.exceptions.NotFoundLocation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LocationDao {
    private final EntityManager entityManager;

    public LocationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Location nuovaLocation) {
        // Instanzio la transaction
        EntityTransaction transaction = this.entityManager.getTransaction();

        //Avvio il metodo in un try catch
        try {
            transaction.begin(); // inizio la transaction
            this.entityManager.persist(nuovaLocation); // aggiungo la location
            transaction.commit(); // committo a db
            System.out.println("La location : " + nuovaLocation.getNome() + " è stata salvata a db");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta " + e.getMessage());
        }
    }

    public Location getById(long id) {
        Location found = this.entityManager.find(Location.class, id);
        if (found == null) {
            throw new NotFoundLocation("La location con Id: " + id + "non è stata trovata");
        }
        return found;

    }
}
