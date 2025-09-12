package com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EmprestimoDTO(@NotNull Integer id, @NotNull String equipamento, @NotNull LocalDate dataEmprestimo,
		@NotNull LocalDate dataDevolucao) {

}
