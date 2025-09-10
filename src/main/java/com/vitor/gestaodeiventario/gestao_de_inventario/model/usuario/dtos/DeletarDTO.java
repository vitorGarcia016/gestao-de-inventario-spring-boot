package com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos;

import jakarta.validation.constraints.NotBlank;

public record DeletarDTO(@NotBlank String senha) {

}
