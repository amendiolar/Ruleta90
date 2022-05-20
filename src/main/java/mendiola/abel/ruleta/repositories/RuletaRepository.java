package mendiola.abel.ruleta.repositories;

import mendiola.abel.ruleta.models.entities.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta,Long>
{

}
