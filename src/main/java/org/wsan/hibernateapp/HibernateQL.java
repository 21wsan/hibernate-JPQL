package org.wsan.hibernateapp;

import jakarta.persistence.EntityManager;
import org.wsan.hibernateapp.dominio.ClienteDto;
import org.wsan.hibernateapp.entity.Cliente;
import org.wsan.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateQL {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("========== listar todos ==========");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consultar por Id ==========");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente);

        System.out.println("========== consulta solo el nombre por id ==========");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id=:id", String.class)
                .setParameter("id", 2L)
                .getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("========== consultas por campos personalizados ==========");
        Object[] objetoCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();

        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[2];
        System.out.println("id=" + id + ",nombre=" + nombre + ",apellido=" + apellido);

        System.out.println("========== consultas por campos personalizados lista ==========");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();
        for(Object[] reg : registros){
            id = (Long) reg[0];
            nombre = (String) reg[1];
            apellido = (String) reg[2];
            System.out.println("id=" + id + ",nombre=" + nombre + ",apellido=" + apellido);
        }
        System.out.println("========== consulta de cliente y forma de pago ==========");
        registros = em.createQuery("select c, formaPago from Cliente c", Object[].class)
                .getResultList();

        registros.forEach(reg -> {
            Cliente c = (Cliente)reg[0];
            String formaPago = (String)reg[1];
            System.out.println("formaPago=" + formaPago + "," + c);
        });

        System.out.println("========== consulta que puebla y devuelve objeto entity de una clase personalizada ==========");
        clientes = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                        .getResultList();

        clientes.forEach(System.out::println);

        System.out.println("========== consulta que puebla y devuelve objeto otro de una clase personalizada ==========");
        List<ClienteDto> clientesDto = em.createQuery("select new org.wsan.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                        .getResultList();
        clientesDto.forEach(System.out::println);

        System.out.println("========== consulta con nombres de clientes ==========");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con nombres unicos de clientes ==========");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con forma de pago unicas ==========");
        List<String> formaPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con forma de pago unicas ==========");
        Long totalFormaPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
                .getSingleResult();
        System.out.println(totalFormaPago);

        System.out.println("========== consulta con nombre y apellido concatenados ==========");
        nombres = em.createQuery("select c.nombre || ' ' || c.apellido as nombreCompleto from Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con nombre y apellido concatenados en mayuscula ==========");
        nombres = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta para buscar por nombre ==========");
        String param = "LU";
        clientes = em.createQuery("select c from Cliente c where upper(c.nombre) like upper(:parametro)", Cliente.class)
                .setParameter("parametro", "%" + param + "%")
                        .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consulta por rangos ==========");
        //clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5",Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'Q'",Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consulta con ordenamiento ==========");
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc, c.apellido asc", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consulta con total de registros de la tabla ==========");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println(total);

        System.out.println("========== consulta con valor minimo del ID ==========");
        Long minId = em.createQuery("select min(c.id) as minimo from Cliente as c", Long.class).getSingleResult();
        System.out.println(minId);

        System.out.println("========== consulta con max / ultimo ID ==========");
        Long maxId = em.createQuery("select max(c.id) as max from Cliente as c", Long.class).getSingleResult();
        System.out.println(maxId);

        System.out.println("========== consulta con nombre y su largo ==========");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("nombre: " + nom + ", largo: " + largo);
        });

        System.out.println("========== consulta con el nombre mas corto ==========");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(minLargoNombre);

        System.out.println("========== consulta con el nombre mas largo ==========");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);

        System.out.println("========== consulta resumen funciones agregaciones count min max avg sum ==========");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                .getSingleResult();
        Long min = (Long) estadisticas[0];
        Long max = (Long) estadisticas[1];
        Long sum = (Long) estadisticas[2];
        Long count = (Long) estadisticas[3];
        Double avg = (Double) estadisticas[4];
        System.out.println("min=" + min + ", max=" + max + ", sum=" + sum + ", count=" + count + ", avg=" + avg);

        em.close();
    }
}
