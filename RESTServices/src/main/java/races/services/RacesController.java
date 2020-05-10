package races.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import races.factory.HibernateFactory;
import races.model.Race;
import races.repositories.RaceRepository;
import races.repositories.RepositoryException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/races")
public class RacesController {

    @Autowired
    private RaceRepository raceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        try{
            Iterable<Race> result=raceRepository.findAll();
            List<Race> supp=new ArrayList<>();
            for (Race r:result
                 ) {
                supp.add(r);
            }
            return new ResponseEntity<>(supp.toArray(),HttpStatus.OK);
        }catch (RepositoryException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){
        try{
            Race race=raceRepository.findOne(id);
            return new ResponseEntity<>(race,HttpStatus.OK);
        }catch (RepositoryException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Race race){
        try{
            raceRepository.save(race);
            return new ResponseEntity<>("Save succesful",HttpStatus.OK);
        }catch (RepositoryException e){
            return new ResponseEntity<>("Could not save because "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Race race){
        try{
            raceRepository.update(race.getId(),race);
            return new ResponseEntity<>("Update succesful",HttpStatus.OK);
        }catch(RepositoryException e){
            return new ResponseEntity<>("Could not update because "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            raceRepository.delete(id);
            return new ResponseEntity<>("Delete succesful",HttpStatus.OK);
        }catch (RepositoryException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
