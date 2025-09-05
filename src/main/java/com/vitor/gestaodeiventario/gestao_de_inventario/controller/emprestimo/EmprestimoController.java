package com.vitor.gestaodeiventario.gestao_de_inventario.controller.emprestimo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.gestaodeiventario.gestao_de_inventario.service.emprestimo.EmprestimoService;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@PostMapping("/{id}")
	public ResponseEntity<String> postEmprestimo(@PathVariable(name = "id") Integer id) {
		return emprestimoService.solicitarEmprestimo(id);
	}

	@GetMapping
	public ResponseEntity<?> getEmprestimo() {
		return emprestimoService.obterEmprestimosFuncionario();
	}

}
