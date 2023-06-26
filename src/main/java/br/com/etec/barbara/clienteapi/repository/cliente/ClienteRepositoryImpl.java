package br.com.etec.barbara.clienteapi.repository.cliente;

import br.com.etec.barbara.clienteapi.model.Cliente;
import br.com.etec.barbara.clienteapi.repository.filter.ClienteFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Cliente> Filtrar(ClienteFilter clientefilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        Predicate[] predicates = criarRestricoes(clientefilter, builder, root);
        criteria.where(predicates);

        criteria.orderBy(builder.asc(root.get("nomecliente")));
        TypedQuery<Cliente> query = manager.createQuery(criteria);

        adicionarRestricoesDePagianacao(query, pageable);



        return new PageImpl<>(query.getResultList(),pageable, total(clientefilter));
    }

    private Long total(ClienteFilter clientefilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        Predicate[] predicates = criarRestricoes(clientefilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomecliente")));

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }


    private void adicionarRestricoesDePagianacao(TypedQuery<Cliente> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPágina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroPágina);
        query.setMaxResults(totalRegistrosPorPagina);

    }


    private Predicate[] criarRestricoes(ClienteFilter clientefilter, CriteriaBuilder builder, Root<Cliente> root) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(clientefilter.getNomecliente())){
            predicates.add(builder.like(builder.lower(root.get("nomecliente")),
                    "%" + clientefilter.getNomecliente().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);

    }


}
