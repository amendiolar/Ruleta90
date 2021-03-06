package mendiola.abel.ruleta.controllers;

import mendiola.abel.ruleta.exceptions.BadRequestException;
import mendiola.abel.ruleta.exceptions.NotFoundException;
import mendiola.abel.ruleta.models.entities.Apuesta;
import mendiola.abel.ruleta.models.entities.Ruleta;
import mendiola.abel.ruleta.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruleta")
public class RuletaController
{
    @Autowired
    private RuletaDAO ruletaDao;

    /**
     * 1. Endpoint de creación de nuevas ruletas que devuelva el id de la nueva ruleta creada
     * @return Retorna el id de la nueva ruleta creada
     * @BadRequestException En caso de que no se solicite correctamente
     * @author AMR - 17-mayo-2022
     */
    @PostMapping("/crear")
    public ResponseEntity<?> save(@Valid @RequestBody Ruleta ruleta, BindingResult result)
    {
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }

        Ruleta ruletaGuardada = ruletaDao.save(ruleta);

        return new ResponseEntity<Ruleta>(ruletaGuardada, HttpStatus.CREATED);
    }

    /**
     *
     2. Endpoint de apertura de ruleta (el input es un id de ruleta) que permita las
     posteriores peticiones de apuestas, este debe devolver simplemente un estado
     queconfirme que la operación fue exitosa o denegada
     * @param
     * @return
     */
    @PutMapping("/apertura/ruletaId/{ruletaId}")
    public Ruleta openRuleta(@PathVariable("ruletaId") Long id)
    {
        return ruletaDao.openRuleta(id);

    }


    /**
     *
     3. Endpoint de apuesta a un número (los números válidos para apostar son del 0 al
     36)o color (negro o rojo) de la ruleta una cantidad determinada de dinero (máximo
     10.000 dólares) a una ruleta abierta.
     * @param
     * @param
     * @return
     */

    @PostMapping("/apostar/{ruletaId}")
    public Apuesta saveApuesta(@PathVariable("ruletaId") Long id, @Valid @RequestBody  Apuesta entidad )
    {
        return ruletaDao.saveApuesta(entidad, id);
    }


    /**
     * 4. Endpoint de cierre apuestas dado un id de ruleta, este Endpoint debe devolver
     * el resultado de las apuestas hechas desde su apertura hasta el cierre.
     * @return
     * @NotFoundException En caso de que no encuentre ningun elemento en la base de datos
     * @author AMR - 17-mayo-2022
     */

    @PutMapping("/cerrar/{ruletaId}")
    public Iterable<Apuesta> closeRuleta(@RequestBody Ruleta ruleta, @PathVariable("ruletaId") Long id)
    {
        ruletaDao.closeRuleta(ruleta,id);
        return ruletaDao.buscarApuestasPorRuleta(id);
    }

    /**
     * 5. Endpoint de listado de ruletas creadas con sus estados (abierta o cerrada)
     * @return
     * @NotFoundException En caso de que no encuentre ningun elemento en la base de datos
     * @author AMR - 17-mayo-2022
     */

    @GetMapping("/listadoRuletas")
    public List<Ruleta> fetchRuletasList()
    {
        return ruletaDao.fetchRuletasList();
    }

}
