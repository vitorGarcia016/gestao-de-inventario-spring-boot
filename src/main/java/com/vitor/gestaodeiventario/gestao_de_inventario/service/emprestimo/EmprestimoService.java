package com.vitor.gestaodeiventario.gestao_de_inventario.service.emprestimo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EquipamentoIndisponivelException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FalhaAoObterEmprestimosException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FalhaAoSolicitarEmprestimoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.Emprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.StatusEmprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.dto.EmprestimoDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.StatusEquipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.emprestimo.EmprestimoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento.EquipamentoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepositorie emprestimoRepositorie;

	@Autowired
	private EquipamentoRepositorie equipamentoRepositorie;

	@Autowired
	private UsuarioRepositorie usuarioRepositorie;

	public ResponseEntity<String> solicitarEmprestimo(Integer idEquipamento) {

		String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

		Usuario usuario = usuarioRepositorie.findByEmail(emailUsuario)
				.orElseThrow(() -> new FuncionarioInvalidoException());

		Equipamento equipamento = equipamentoRepositorie.findById(idEquipamento)
				.orElseThrow(() -> new EquipamentoNaoEncontradoException());

		if (equipamento.getStatus() == StatusEquipamento.INDISPONIVEL) {
			throw new EquipamentoIndisponivelException();
		}

		try {

			LocalDate dataAtual = LocalDate.now();

			LocalDate dataDevolucao = dataAtual.plusDays(10);

			equipamento.setStatus(StatusEquipamento.INDISPONIVEL);

			Emprestimo emprestimo = new Emprestimo(usuario, equipamento, dataAtual, dataDevolucao,
					StatusEmprestimo.PENDENTE);

			emprestimoRepositorie.save(emprestimo);

			return ResponseEntity.ok().body("Solicitacao enviada \n Data de devolucao: " + dataDevolucao);

		} catch (Exception e) {
			throw new FalhaAoSolicitarEmprestimoException();
		}

	}

	public ResponseEntity<?> obterEmprestimosFuncionario() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Usuario usuario = usuarioRepositorie.findByEmail(email).orElseThrow(() -> new FuncionarioInvalidoException());

		try {
			List<Emprestimo> emprestimos = emprestimoRepositorie.findAllByUsuario_idAndStatus(usuario.getId(),
					StatusEmprestimo.PENDENTE);

			List<EmprestimoDTO> dto = emprestimos.stream().map(
					e -> new EmprestimoDTO(e.getEquipamento().getNome(), e.getDataEmprestimo(), e.getDataDevolucao()))
					.toList();

			return ResponseEntity.ok().body(dto);

		} catch (Exception e) {
			throw new FalhaAoObterEmprestimosException();
		}

	}

}
