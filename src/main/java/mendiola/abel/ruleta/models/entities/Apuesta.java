package mendiola.abel.ruleta.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "apuestas", schema = "ruleta")
public class Apuesta implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "colores")
    private String color;

    @Column(name = "numeros")
    private Integer numero;

    @Column(name = "valorApuestas")
    private Double valorApuesta;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @ManyToOne(optional = true,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ruleta_id", foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;

    public Apuesta(Long id, String color, Integer numero, Double valorApuesta) {
        this.id = id;
        this.color = color;
        this.numero = numero;
        this.valorApuesta = valorApuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apuesta)) return false;
        Apuesta apuesta = (Apuesta) o;
        return Objects.equals(getId(), apuesta.getId()) && Objects.equals(getColor(), apuesta.getColor()) && Objects.equals(getNumero(), apuesta.getNumero()) && Objects.equals(getValorApuesta(), apuesta.getValorApuesta()) && Objects.equals(getFechaAlta(), apuesta.getFechaAlta()) && Objects.equals(getRuleta(), apuesta.getRuleta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getNumero(), getValorApuesta(), getFechaAlta(), getRuleta());
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaAlta = new Date();
    }
}
