package races.repositories;

public interface CRUDRepository<ID,T>  {
    void save(T entity)throws RepositoryException;
    void delete(ID id)throws RepositoryException;
    void update(ID id, T entity)throws RepositoryException;
    T findOne(ID id)throws RepositoryException;
    Iterable<T> findAll()throws RepositoryException;
}

