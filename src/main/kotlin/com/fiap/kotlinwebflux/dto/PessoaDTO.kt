package com.fiap.kotlinwebflux.dto

// var para variavel
// val para constante

data class PessoaDTO (
        val id: Int? = null,
        val nome: String? = null,
        val cpf: String? =null
)
