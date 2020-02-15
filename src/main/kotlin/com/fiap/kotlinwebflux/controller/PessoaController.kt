package com.fiap.kotlinwebflux.controller

import com.fiap.kotlinwebflux.dto.PessoaDTO
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.awt.PageAttributes
import java.time.Duration

@RestController
class PessoaController {

    /*@GetMapping
    fun getPessoas(): Flux<PessoaDTO>  = Flux.create{ emitter->
        emitter.next(PessoaDTO(1, "Jaqueline", "12345664"))
        emitter.next(PessoaDTO(1, "Joao", "12345664"))
        emitter.complete()
    }*/

    // Neste exemplo o banco retorna de forma reativa
    // a medida que ele vai completando ele vai mostrando
    @GetMapping(produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getPessoas(): Flux<PessoaDTO>  = Flux.just(
        arrayListOf(
                PessoaDTO(1,"Jaque", "1234566"),
                PessoaDTO(1,"Jaque", "1234566"),
                PessoaDTO(1,"Jaque", "1234566")
        )
    )
            .flatMapIterable { it }
            .delayElements(Duration.ofSeconds(2))


}

// Kotlin assim como java é uma linguagem fortamente tipada
// se definirmos uma variavel do tipo String nao e possivel atribuir um numero para ele
// Tipagem por inferencia bastante utilizado no Kotlin


// A diferenca do Webflux para o Spring normal é que nao se retorna mais List
// ou vc retorna um Mono ou um Flux
// sao dois objetos reativos > depende da base que voce esta trabalhando
// Mono > Uma emissao apenas (acontece e acaba)

// Flux > Pode ter varias emissoes

// Vantagem de utilizar programacao reativa e para gerenciar as threadas
// deste modo, os registros nao ficam em Lock
// Utilizando os callback nenhuma thread fica locada atraves do @Subscribe
