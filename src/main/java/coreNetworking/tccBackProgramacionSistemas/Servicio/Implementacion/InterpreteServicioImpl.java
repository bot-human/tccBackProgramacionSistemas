package coreNetworking.tccBackProgramacionSistemas.Servicio.Implementacion;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Interprete;
import coreNetworking.tccBackProgramacionSistemas.Repositorio.IRepository;
import coreNetworking.tccBackProgramacionSistemas.Repositorio.IInterpreteRepository;
import coreNetworking.tccBackProgramacionSistemas.Servicio.IInterpreteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterpreteServicioImpl extends ServicioImp<Interprete, Integer> implements IInterpreteServicio {

    @Autowired
    private IInterpreteRepository InterpreteRepositorio;

    @Override
    protected IRepository<Interprete, Integer> getRepo() {
        return InterpreteRepositorio;
    }


    @Override
    public Interprete findByInterpretePorNombre(String nombre) {
        Interprete interpretes = InterpreteRepositorio.findByInterpretePorNombre(nombre);
        if (interpretes == null){
            return null;
        }else {
            return interpretes;
        }
    }

    @Override
    public void borrarInterpretePorNombre(String nombre) {
        Interprete interprete = InterpreteRepositorio.findByInterpretePorNombre(nombre);
        InterpreteRepositorio.borrarInterpretePorNombre(interprete.getNombre());
    }

    @Override
    public Interprete save(Interprete interprete) {
        return InterpreteRepositorio.save(interprete);
    }


}
