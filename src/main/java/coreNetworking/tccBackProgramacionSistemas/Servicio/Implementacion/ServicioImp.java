package coreNetworking.tccBackProgramacionSistemas.Servicio.Implementacion;

import coreNetworking.tccBackProgramacionSistemas.Repositorio.IRepository;
import coreNetworking.tccBackProgramacionSistemas.Servicio.IServicio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class ServicioImp<T,ID> implements IServicio<T,ID> {

    protected abstract IRepository<T,ID> getRepo();

    @Autowired
    private EntityManager entityManager;

    @Override
    public T registrar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T modificar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> listar() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T listarPorId(ID id) throws Exception {
        Optional<T> t1= getRepo().findById(id);
        return t1.isPresent() ? t1.get() : null;
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().deleteById(id);

    }
}
