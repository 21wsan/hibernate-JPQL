package org.wsan.hibernateapp.util;

import jakarta.persistence.EntityManager;
import org.wsan.hibernateapp.entity.Cliente;
import org.wsan.hibernateapp.services.ClienteService;
import org.wsan.hibernateapp.services.ClienteServiceImp;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImp(em);

        System.out.println("========== listar ==========");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("========== obtener por Id ==========");
        Optional<Cliente> optionalCliente = service.porId(1L);
        optionalCliente.ifPresent(System.out::println);

        em.close();
    }
}
