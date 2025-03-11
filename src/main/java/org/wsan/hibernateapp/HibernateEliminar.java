package org.wsan.hibernateapp;

import jakarta.persistence.EntityManager;
import org.wsan.hibernateapp.entity.Cliente;
import org.wsan.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateEliminar {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a eliminar:");
        Long id = sc.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Cliente cliente = em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
