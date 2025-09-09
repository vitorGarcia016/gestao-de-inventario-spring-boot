package com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.dto;

import java.time.LocalDate;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.StatusEmprestimo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ObterEmprestimosDTO(@NotNull Integer id, @NotBlank String nome, @NotNull String equipamento,
		@NotNull LocalDate dataEmprestimo, @NotNull LocalDate dataDevolucao, StatusEmprestimo status) {

}
