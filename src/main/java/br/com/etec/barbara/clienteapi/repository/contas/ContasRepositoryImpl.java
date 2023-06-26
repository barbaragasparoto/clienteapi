package br.com.etec.barbara.clienteapi.repository.contas;

import br.com.etec.barbara.clienteapi.model.Contas;
import br.com.etec.barbara.clienteapi.repository.Dtos.ContasDto;
import br.com.etec.barbara.clienteapi.repository.filter.ContasFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ContasRepositoryImpl implements ContasRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ContasDto> filtrar(ContasFilter contasfilter, Pageable pageable) {


        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ContasDto> criteria = builder.createQuery(ContasDto.class);
        Root<Contas> root = criteria.from(Contas.class);

        criteria.select(builder.construct(ContasDto.class,
                root.get("id"),
                root.get("data"),
                root.get("valor"),
                root.get("cliente").get("nomecliente")
        ));


        Predicate[] predicates = criarRestricoes(contasfilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("data")));

        TypedQuery<ContasDto> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);



        return new PageImpl<>(query.getResultList(), pageable, total(contasfilter));
    }

    private Long total(ContasFilter contasfilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Contas> root = criteria.from(Contas.class);

        Predicate[] predicates = criarRestricoes(contasfilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("data")));

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPágina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroPágina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Predicate[] criarRestricoes(ContasFilter contasfilter, CriteriaBuilder builder, Root<Contas> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (contasfilter.getValor() != null){
            predicates.add(builder.equal(root.get("valor"), contasfilter.getValor()));
        }

        if (contasfilter.getData() != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("data"), contasfilter.getData()));
        }

        if(!StringUtils.isEmpty(contasfilter.getNomecliente())){
            predicates.add(builder.like(builder.lower(root.get("cliente").get("nomecliente")),
                    "%" + contasfilter.getNomecliente().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
