package com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.emprestimo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.Emprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.StatusEmprestimo;

@Repository
public interface EmprestimoRepositorie extends JpaRepository<Emprestimo, Integer>{

	
	List<Emprestimo> findAllByUsuario_idAndStatus(Integer id, StatusEmprestimo s);
}
