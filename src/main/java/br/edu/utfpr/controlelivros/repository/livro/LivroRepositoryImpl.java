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
import org.springframework.util.StringUtils;

import br.edu.utfpr.controlelivros.model.Livro;
import br.edu.utfpr.controlelivros.repository.filter.LivroFilter;

public class LivroRepositoryImpl implements LivroRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable) {
		
		CriteriaBuilder cBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Livro> cQuery = cBuilder.createQuery(Livro.class);
		
		Root<Livro> root = cQuery.from(Livro.class);
		
		Predicate[] predicates = criarRestricoes(livroFilter, cBuilder, root);
		cQuery.where(predicates);
		
		TypedQuery<Livro> tQuery = manager.createQuery(cQuery);
		
		adicionarRestricoesPaginacao(tQuery, pageable);
		
		//return tQuery.getResultList();
		return new PageImpl<>(tQuery.getResultList(), pageable, total(livroFilter));
	}

	private Predicate[] criarRestricoes(LivroFilter livroFilter, CriteriaBuilder cBuilder, Root<Livro> root) {
		List<Predicate> predicates = new ArrayList<>();
		
				
		if(StringUtils.hasText(livroFilter.getTitulo())) {
			predicates.add(cBuilder.like(cBuilder.lower(root.get("titulo")), "%" +livroFilter.getTitulo().toLowerCase() + "%"));
		}
		if(StringUtils.hasText(livroFilter.getAutor())) {
			predicates.add(cBuilder.like(cBuilder.lower(root.get("autor")), "%" +livroFilter.getAutor().toLowerCase() + "%"));
		}
		if(StringUtils.hasText(livroFilter.getStleitura())) {
			predicates.add(cBuilder.like(cBuilder.lower(root.get("stleitura")), "%" +livroFilter.getStleitura().toLowerCase() + "%"));
		}
		
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

	private void adicionarRestricoesPaginacao(TypedQuery<Livro> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totRegistrosPorPags = pageable.getPageSize();
		int primeiroRegistroDaPag = paginaAtual * totRegistrosPorPags;
		
		query.setFirstResult(primeiroRegistroDaPag);
		query.setMaxResults(totRegistrosPorPags);
		
	}

}
