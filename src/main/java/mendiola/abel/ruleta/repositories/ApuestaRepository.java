package mendiola.abel.ruleta.repositories;

import mendiola.abel.ruleta.models.entities.Apuesta;
import mendiola.abel.ruleta.models.entities.Ruleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuestaRepository extends CrudRepository<Apuesta,Long>
{
    @Query("select a from Apuesta a join fetch a.ruleta r where r.id = ?1")
    public Iterable<Apuesta> buscarApuestasPorRuleta(Long ruletaId);
}
