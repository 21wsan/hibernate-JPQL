package org.wsan.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.wsan.hibernateapp.entity.Cliente;
import org.wsan.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //forma # 1
        /*EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.id=?1", Cliente.class);
        System.out.print("Ingrese el ID: ");
        Long pago = sc.nextLong();
        query.setParameter(1,pago);
        Cliente c = (Cliente) query.getSingleResult();
        System.out.print(c);
        em.close();
        */

        //forma # 2
        System.out.print("Ingrese el ID: ");
        Long id = sc.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);

        Cliente cliente2 = em.find(Cliente.class, id);
        System.out.println(cliente2);

        em.close();
    }
}
