package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.RepositorioUsuario;

public class ExcluirUsuario {

        private final RepositorioUsuario repositorioUsuario;

        public ExcluirUsuario(RepositorioUsuario repositorioUsuario) {
            this.repositorioUsuario = repositorioUsuario;
        }

        public void excluirUsuario(String cpf) {
            repositorioUsuario.excluiUsuario(cpf);
        }
}
