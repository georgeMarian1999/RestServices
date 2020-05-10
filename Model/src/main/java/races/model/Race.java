package races.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity(name = "Race")
@Table(name="Race")
public class Race implements  Serializable{
    @Id
    private int id;
    @Column(name = "capacity")
    private int capacity;

    public Race(){

    }
    public Race(int i,int c){
        this.id=i;
        this.capacity=c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
