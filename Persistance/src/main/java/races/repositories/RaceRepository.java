package races.repositories;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import races.JDBCUtils.JDBCUtils;
import races.factory.HibernateFactory;
import races.model.Race;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

@Component
public class RaceRepository implements IRaceRepository {


    private JDBCUtils utils=new JDBCUtils(getProps());

    private Properties getProps(){
        Properties props = new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public RaceRepository(){

    }

    @Override
    public void save(Race entity) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Race values (?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setInt(2,entity.getCapacity());
            int result=preStmt.executeUpdate();

        }catch(SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }

    @Override
    public void delete(Integer integer) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Race where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }
        catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }

    @Override
    public void update(Integer integer, Race entity) throws RepositoryException {
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Race set id=?,capacity=? where id=?")){
            preStmt.setInt(1,entity.getId());
            preStmt.setInt(2,entity.getCapacity());
            preStmt.setInt(3,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }

    }

    @Override
    public Race findOne(Integer integer) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Race where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    Integer id,capac;
                    id=result.getInt("id") ;
                    capac=result.getInt("capacity");
                    Race C=new Race(id,capac);
                    return C;
                }
            }

        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
        return null;
    }

    @Override
    public Iterable<Race> findAll() throws RepositoryException{
        Connection con=utils.getConnection();
        List<Race> Curse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Race")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int capac;
                    capac=result.getInt("capacity");
                    Race P=new Race(id,capac);
                    Curse.add(P);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return Curse;
    }
}
