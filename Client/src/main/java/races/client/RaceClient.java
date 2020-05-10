package races.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import races.model.Race;
import races.services.ServicesException;

import java.util.concurrent.Callable;

public class RaceClient {
    private final String URL="http://localhost:8080/races";

    private RestTemplate restTemplate=new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) {
            throw new ServicesException(e);
        } catch (Exception e) {
            throw new ServicesException(e);
        }
    }
    public Race[] getAll(){
        return execute(()->restTemplate.getForObject(URL,Race[].class));
    }
    public Race getById(int id){
        String newurl=String.format("%s/%s",URL,String.valueOf(id));
        return execute(()->restTemplate.getForObject(newurl,Race.class));
    }
    public ResponseEntity<?> create(Race race){
        return execute(()->restTemplate.postForObject(URL,race,ResponseEntity.class));
    }
    public void update(Race race){
        String newurl=String.format("%s/%s",URL,String.valueOf(race.getId()));
         execute(()->{
             restTemplate.put(newurl,race);
             return null;
         });
    }
    public void delete(int id){
        String newurl=String.format("%s/%s",URL,String.valueOf(id));
        execute(()->{
           restTemplate.delete(newurl,id);
           return null;
        });
    }
}
