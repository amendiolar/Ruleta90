package mendiola.abel.ruleta.services;

import mendiola.abel.ruleta.exceptions.BadRequestException;
import mendiola.abel.ruleta.exceptions.NotFoundException;
import mendiola.abel.ruleta.models.entities.Apuesta;
import mendiola.abel.ruleta.models.entities.Ruleta;
import mendiola.abel.ruleta.repositories.ApuestaRepository;
import mendiola.abel.ruleta.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RuletaDAOImpl implements RuletaDAO
{
    @Autowired(required = true)
    RuletaRepository ruletaRepository;

    @Autowired
    ApuestaRepository apuestaRepository;

    @Override
    @Transactional
    public Ruleta save(Ruleta entidad) {
        return ruletaRepository.save(entidad);
    }

    @Override
    public Ruleta openRuleta(Long id)
    {
        Ruleta ruleta2Open = ruletaRepository.findById(id).orElseThrow(()-> new NotFoundException("La ruleta con ID %d no existe", id) );


        if(ruleta2Open.getEstaAbierta())
        {
            throw new BadRequestException("La ruleta ya está abierta");
        }
        ruleta2Open.setEstaAbierta(true);
        return ruletaRepository.save(ruleta2Open);
    }

    @Override
    @Transactional
    public Apuesta saveApuesta(Apuesta entidad, Long id)
    {
        Ruleta ruleta2Open = ruletaRepository.findById(id).orElseThrow(()-> new NotFoundException("La ruleta con ID %d no existe", id) );

        if(!ruleta2Open.getEstaAbierta())
        {
            throw new BadRequestException("La ruleta está cerrada");
        }

        entidad.setRuleta(ruleta2Open);

        return apuestaRepository.save(entidad);

    }

    @Override
    @Transactional
    public Ruleta closeRuleta(Ruleta ruleta, Long id)
    {
        Ruleta ruleta2Close = ruletaRepository.findById(id).orElseThrow(()-> new NotFoundException("La ruleta con ID %d no existe", id) );


        if(!ruleta2Close.getEstaAbierta())
        {
            throw new BadRequestException("La ruleta ya está cerrada");
        }
        ruleta2Close.setEstaAbierta(ruleta.getEstaAbierta());

        return ruletaRepository.save(ruleta2Close);

    }

    @Override
    @Transactional
    public Iterable<Apuesta> buscarApuestasPorRuleta(Long ruletaId)
    {
        return apuestaRepository.buscarApuestasPorRuleta(ruletaId);
    }

    @Override
    public List<Ruleta> fetchRuletasList()
    {
        return (List<Ruleta>) ruletaRepository.findAll();
    }

}
