package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Funcionario;
import com.projetojpa.service.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Funcionario", description= "API REST DE GERENCIAMENTO DE PEDIDO")
@RestController
@RequestMapping("/funcionario")

public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localizar livro por ID")
	public ResponseEntity<Funcionario> buscaFuncionarioControlId(@PathVariable Long id){
		Funcionario funcionario = funcionarioService.buscaFuncionarioId(id);
		if(funcionario != null) {
			return ResponseEntity.ok(funcionario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos livros")
	public ResponseEntity<List<Funcionario>> buscaTodosPedidoControl(){
		List<Funcionario> Funcionario= funcionarioService.buscaTodosFuncionario();
		return ResponseEntity.ok(Funcionario);
	}
	@PostMapping("/")
	@Operation(summary = "Cadastrar um pedido")
	public ResponseEntity<Funcionario> salvaFuncionarioControl(@RequestBody @Valid Funcionario funcionario) {
		Funcionario salvaFuncionario = funcionarioService.salvaFuncionario(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaFuncionario);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Alterar um pedido")
	public ResponseEntity<Funcionario> alterarFuncionarioControl(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario){
	Funcionario alteraFuncionario = funcionarioService.alterarFuncionario(id, funcionario);
	if(alteraFuncionario != null) {
		return ResponseEntity.ok(funcionario);
	}
	else {
		return ResponseEntity.notFound().build();
	}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir um pedido")
	public ResponseEntity<String> apagarFuncionarioControl(@PathVariable Long id) {
		boolean apagar = funcionarioService.apagarFuncionario(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	}
	else {
		return ResponseEntity.notFound().build();    
	}
	}
}