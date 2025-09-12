package com.vitor.gestaodeiventario.gestao_de_inventario.service.emprestimo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EmprestimoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EquipamentoIndisponivelException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FalhaAoObterEmprestimosException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FalhaAoRegistrarDevolucaoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FalhaAoSolicitarEmprestimoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FuncionarioNaoCorrespondenteException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.Emprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.StatusEmprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.dtos.EmprestimoDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.dtos.ObterEmprestimosDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.StatusEquipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.emprestimo.EmprestimoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento.EquipamentoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.util.BD;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepositorie emprestimoRepositorie;

	@Autowired
	private EquipamentoRepositorie equipamentoRepositorie;

	
	@Autowired
	private BD util;

	public ResponseEntity<String> solicitarEmprestimo(Integer idEquipamento) {

		Usuario usuario = util.obterUsuarioDaVezBd();

		Equipamento equipamento = equipamentoRepositorie.findById(idEquipamento)
				.orElseThrow(() -> new EquipamentoNaoEncontradoException());

		if (equipamento.getStatus() == StatusEquipamento.INDISPONIVEL) {
			throw new EquipamentoIndisponivelException();
		}

		try {

			LocalDate dataAtual = LocalDate.now();

			LocalDate dataDevolucao = dataAtual.plusDays(10);

			equipamento.setStatus(StatusEquipamento.INDISPONIVEL);

			Emprestimo emprestimo = new Emprestimo(usuario, equipamento, dataAtual, dataDevolucao, null,
					StatusEmprestimo.PENDENTE);

			emprestimoRepositorie.save(emprestimo);

			return ResponseEntity.ok().body("Solicitacao enviada \n Data de devolucao: " + dataDevolucao);

		} catch (Exception e) {
			throw new FalhaAoSolicitarEmprestimoException();
		}

	}

	public ResponseEntity<?> obterEmprestimosFuncionario() {

		Usuario usuario = util.obterUsuarioDaVezBd();

		try {
			List<Emprestimo> emprestimos = emprestimoRepositorie.findAllByUsuario_idAndStatus(usuario.getId(),
					StatusEmprestimo.PENDENTE);

			List<EmprestimoDTO> dto = emprestimos.stream().map(e -> new EmprestimoDTO(e.getEquipamento().getId(),
					e.getEquipamento().getNome(), e.getDataEmprestimo(), e.getDataPrevista())).toList();

			return ResponseEntity.ok().body(dto);

		} catch (Exception e) {
			throw new FalhaAoObterEmprestimosException();
		}

	}

	public ResponseEntity<String> devolverEmprestimo(Integer idEmprestimo) {

		Usuario usuario = util.obterUsuarioDaVezBd();

		Emprestimo emprestimo = emprestimoRepositorie.findById(idEmprestimo)
				.orElseThrow(() -> new EmprestimoNaoEncontradoException());

		if (usuario.getId() != emprestimo.getUsuario().getId()) {
			throw new FuncionarioNaoCorrespondenteException();
		}

		try {
			emprestimo.setDataDevolvolucao(LocalDate.now());

			if (emprestimo.getDataDevolvolucao().isAfter(emprestimo.getDataPrevista())) {
				emprestimo.setStatus(StatusEmprestimo.ATRASADO);
			} else {
				emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
			}

			emprestimoRepositorie.save(emprestimo);

			return ResponseEntity.ok().body("Devolucao registrada \nStatus: " + emprestimo.getStatus());

		} catch (Exception e) {
			throw new FalhaAoRegistrarDevolucaoException();
		}

	}

	public ResponseEntity<?> obterEmprestimosAtrasados() {

		try {

			List<Emprestimo> emprestimos = emprestimoRepositorie.findAllByStatus(StatusEmprestimo.ATRASADO);

			List<ObterEmprestimosDTO> dto = emprestimos.stream()
					.map(e -> new ObterEmprestimosDTO(e.getId(), e.getUsuario().getNome(), e.getEquipamento().getNome(),
							e.getDataEmprestimo(), e.getDataPrevista(), e.getDataDevolvolucao(), e.getStatus()))
					.toList();

			return ResponseEntity.ok().body(dto);
		} catch (Exception e) {
			throw new FalhaAoObterEmprestimosException();
		}

	}

}
