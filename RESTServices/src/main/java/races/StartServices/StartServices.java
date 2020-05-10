package races.StartServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("races")
@SpringBootApplication
public class StartServices {
    public static void main(String[] args){
        SpringApplication.run(StartServices.class,args);
    }
}
