package org.wsan.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.wsan.hibernateapp.entity.Cliente;
import org.wsan.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class HibernateResultListWhere {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingrese una forma de pago: ");
        String pago = sc.next();
        query.setParameter(1,pago);
        //query.setMaxResults(1);
        List<Cliente> clientes = query.getResultList();
        System.out.print(clientes);
        em.close();
    }
}
