package org.wsan.hibernateapp.services;

import jakarta.persistence.EntityManager;
import org.wsan.hibernateapp.entity.Cliente;
import org.wsan.hibernateapp.repositories.ClienteRepository;
import org.wsan.hibernateapp.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImp implements ClienteService{

    private EntityManager em;
    private CrudRepository<Cliente> repository;

    //contructor
    public ClienteServiceImp(EntityManager em){
        this.em = em;
        this.repository = new ClienteRepository(em);
    }

    @Override
    public List<Cliente> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Cliente cliente) {
        try{
            em.getTransaction().begin();
            repository.guradar(cliente);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
