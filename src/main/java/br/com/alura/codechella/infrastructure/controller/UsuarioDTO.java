package br.com.alura.codechella.infrastructure.controller;

import java.time.LocalDate;

public record UsuarioDTO(
    String cpf,
    String nome,
    LocalDate nascimento,
    String email){}
