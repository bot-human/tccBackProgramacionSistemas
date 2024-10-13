package coreNetworking.tccBackProgramacionSistemas.Servicio;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Interprete;

public interface IInterpreteServicio extends IServicio<Interprete, Integer> {

    Interprete findByInterpretePorNombre(String nombre);

    void borrarInterpretePorNombre(String nombre);

    Interprete save(Interprete interprete);
}
