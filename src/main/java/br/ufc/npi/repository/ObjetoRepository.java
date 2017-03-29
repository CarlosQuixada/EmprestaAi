package br.ufc.npi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bean.Objeto;
@Repository
@Transactional
public interface ObjetoRepository extends JpaRepository<Objeto, Integer> {

}
