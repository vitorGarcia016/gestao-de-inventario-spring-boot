package com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos;

import jakarta.validation.constraints.NotBlank;

public record AtualizarSenhaDTO(@NotBlank String senhaAntiga, @NotBlank String senhaNova) {

}
