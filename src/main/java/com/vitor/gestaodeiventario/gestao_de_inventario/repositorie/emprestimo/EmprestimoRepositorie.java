package com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.Emprestimo;

@Repository
public interface EmprestimoRepositorie extends JpaRepository<Emprestimo, Integer>{

}
