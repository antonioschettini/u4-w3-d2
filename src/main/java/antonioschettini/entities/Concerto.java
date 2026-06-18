package antonioschettini.entities;

import antonioschettini.enums.GenereConcerto;
import antonioschettini.enums.TipoEvento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
//@DiscriminatorValue("CONCERTO")
public class Concerto extends Evento {
    @Enumerated(EnumType.STRING)
    @Column(name = "genere")
    private GenereConcerto genere;

    @Column(name = "In_streaming")
    private boolean inStreaming;

    public Concerto() {
        super();
    }

    public Concerto(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
                    int numeroMassimoPartecipanti, Location location, GenereConcerto genere, boolean inStreaming) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
        this.genere = genere;
        this.inStreaming = inStreaming;
    }

    // Getter e Setter
    public GenereConcerto getGenere() {
        return genere;
    }

    public void setGenere(GenereConcerto genere) {
        this.genere = genere;
    }

    public boolean isInStreaming() {
        return inStreaming;
    }

    public void setInStreaming(boolean inStreaming) {
        this.inStreaming = inStreaming;
    }

    @Override
    public String toString() {
        return "Concerto{" +
                "genere=" + genere +
                ", inStreaming=" + inStreaming +
                '}';
    }
}
