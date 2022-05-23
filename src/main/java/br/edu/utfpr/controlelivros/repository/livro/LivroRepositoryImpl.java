package br.edu.utfpr.controlelivros.repository.livro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.controlelivros.model.Livro;
import br.edu.utfpr.controlelivros.repository.filter.LivroFilter;

public class LivroRepositoryImpl implements LivroRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Livro> cq = cb.createQuery(Livro.class);
		
		Root<Livro> root = cq.from(Livro.class);
		
		Predicate[] predicates = criarRestricoes(livroFilter, cb, root);
		cq.where(predicates);
		
		//Predicate predicate = cb.like(root.get("titulo"), "%" +livroFilter.getTitulo()+ "%");
		//cq.where(predicate);
		
		TypedQuery<Livro> query = manager.createQuery(cq);
		
		adicionarRestricoesPaginacao(query, pageable);
		
		//return query.getResultList();
		return new PageImpl<>(query.getResultList(), pageable, total(livroFilter));
	}

	private Predicate[] criarRestricoes(LivroFilter livroFilter, CriteriaBuilder cb, Root<Livro> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(cb.like(root.get("titulo"), "%" +livroFilter.getTitulo()+ "%"));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(LivroFilter livroFilter) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Livro> root = cq.from(Livro.class);
		
		Predicate[] predicates = criarRestricoes(livroFilter, cb, root);
		cq.where(predicates);
		
		cq.select(cb.count(root));

		return manager.createQuery(cq).getSingleResult();
	}

	private void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totRegistrosPorPags = pageable.getPageSize();
		int primeiroRegistroDaPag = paginaAtual * totRegistrosPorPags;
		
		query.setFirstResult(primeiroRegistroDaPag);
		query.setMaxResults(totRegistrosPorPags);
		
	}

}
