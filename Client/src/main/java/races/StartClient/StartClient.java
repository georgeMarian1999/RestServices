package races.StartClient;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import races.client.RaceClient;
import races.model.Race;
import races.repositories.RepositoryException;
import races.services.ServicesException;

public class StartClient {
    private static final RaceClient raceClient=new RaceClient();
    public static void main(String ... args){
        Race race=new Race(7,1500);
        try{
            show(()->{
                Race[] races=raceClient.getAll();
                for (Race r:races
                     ) {
                    System.out.println(r.getCapacity());
                }
            });
            show(()->raceClient.create(race));
            show(()->{
                Race[] races=raceClient.getAll();
                for (Race r:races
                ) {
                    System.out.println(r.getCapacity());
                }
            });
            show(()->{
                race.setCapacity(1550);
               raceClient.update(race);
            });
            show(()->{
               Race race1=raceClient.getById(7);
                System.out.println(race1.getCapacity());
            });
            show(()->{
                raceClient.delete(7);
            });
            show(()->{
                Race[] races=raceClient.getAll();
                for (Race r:races
                ) {
                    System.out.println(r.getCapacity());
                }
            });


        }catch(RestClientException | RepositoryException e ){
            System.out.println("Error "+e);
        }
    }
    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServicesException e) {
            System.out.println("Services exception"+ e);
        }
    }
}
