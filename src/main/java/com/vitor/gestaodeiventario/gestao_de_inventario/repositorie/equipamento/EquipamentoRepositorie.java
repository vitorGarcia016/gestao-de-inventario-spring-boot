package com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.StatusEquipamento;

@Repository
public interface EquipamentoRepositorie extends JpaRepository<Equipamento, Integer>{

	List<Equipamento> findAllByStatus(StatusEquipamento status);
}
