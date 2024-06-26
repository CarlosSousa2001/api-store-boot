package com.crs.datajpa.model;

import com.crs.datajpa.model.dto.CustomerSignUpDTO;
import com.crs.datajpa.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BasicoJPQLTeste {

    @Autowired
    private EntityManager entityManager;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void buscarPorId(){

        TypedQuery<Order> typedQuery = entityManager
                .createQuery("select o from Order o where o.id = 1", Order.class);

        Order order = typedQuery.getSingleResult();

        Assertions.assertNotNull(order);
    }

    @Test
    public void DirefencaEntreQueries(){
        String jpql = "select o from Order o where o.id = 1";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);

        Order order1 = typedQuery.getSingleResult();

        Assertions.assertNotNull(order1);

        Query query = entityManager.createQuery(jpql);

        Order order2 = (Order) query.getSingleResult();

        Assertions.assertNotNull(order2);
    }

    @Test
    public void selecionarUmAtributoParaRetorno(){
        String jpql = "select p.title from Product p";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);

        List<String> listProduct = typedQuery.getResultList();

        Assertions.assertTrue(String.class.equals(listProduct.get(0).getClass()));

        // pegando o cliente pela order

        String jpqlCliente  = "select o.customer from Order o";

        TypedQuery<Customer> typedQueryCustomer = entityManager.createQuery(jpqlCliente, Customer.class);

        List<Customer> customerList = typedQueryCustomer.getResultList();

        Assertions.assertNotNull(customerList);

    }

    // Projeções


    @Test
    public void projetarResultado(){
        String jpql = "select p.id, p.title from Product p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> objectList = typedQuery.getResultList();

        Assertions.assertEquals(2, objectList.get(0).length);
    }

    // projeções com classeDTO

    @Test
    public void projetarNoDto(){
        String jpqlCustomer  = "select new com.crs.datajpa.model.dto.CustomerDTO(c.id, c.username) from Customer c";

        TypedQuery<CustomerSignUpDTO> typedQuery = entityManager.createQuery(jpqlCustomer, CustomerSignUpDTO.class);

        List<CustomerSignUpDTO> customerSignUpDTO = typedQuery.getResultList();

        Assertions.assertNotNull(customerSignUpDTO);
    }


    // joins

    @Test
    public void fazendoJoin(){
        String jpql = "select o, ps from Order o join o.payment ps";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> order1 = typedQuery.getResultList();

        Assertions.assertNotNull(order1);

    }

}
