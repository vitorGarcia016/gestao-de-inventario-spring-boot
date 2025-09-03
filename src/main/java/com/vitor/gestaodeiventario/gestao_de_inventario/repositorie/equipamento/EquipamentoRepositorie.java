package com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;

@Repository
public interface EquipamentoRepositorie extends JpaRepository<Equipamento, Integer>{

}
