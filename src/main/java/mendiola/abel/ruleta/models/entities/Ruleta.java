package mendiola.abel.ruleta.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ruletas", schema = "ruleta")
public class Ruleta implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estaAbierta")
    private Boolean estaAbierta;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "ruleta",fetch = FetchType.LAZY)
    private List<Apuesta> apuestas;


    public Ruleta(Long id, Boolean estaAbierta) {
        this.id = id;
        this.estaAbierta = estaAbierta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ruleta)) return false;
        Ruleta ruleta = (Ruleta) o;
        return Objects.equals(getId(), ruleta.getId()) && Objects.equals(getEstaAbierta(), ruleta.getEstaAbierta()) && Objects.equals(getFechaAlta(), ruleta.getFechaAlta()) && Objects.equals(getFechaModificacion(), ruleta.getFechaModificacion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEstaAbierta(), getFechaAlta(), getFechaModificacion());
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaAlta = new Date();
    }

    @PreUpdate
    private void antesActualizar()
    {
        this.fechaModificacion = new Date();
    }
}
