package com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.dto;

import java.time.LocalDate;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.StatusEmprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;

import jakarta.validation.constraints.NotNull;

public record EmprestimoDTO(@NotNull String equipamento, @NotNull LocalDate dataEmprestimo,
		@NotNull LocalDate dataDevolucao) {

}
