val menu = """
    
    1 - Adicionar Item
    2 - Editar Item
    3 - Exibir Itens do Estoque
    4 - Exibi Total de Itens
    0 - Sair do App
    
""".trimIndent()

class Item(var id: Int, var nome: String, var quantidade: Int)

val estoque: MutableList<Item> = mutableListOf<Item>()

fun main() {
    println("\nBEM-VINDO AO CONTROLE DE ESTOQUE!")
    println(menu)
    val opcao = readln().toInt()

    when (opcao) {
        1 -> addProduct()
        2 -> editProduct()
        3 -> showInventory()
        4 -> showTotals()
        0 -> endingApp()
        else -> Unit
    }
}


fun addProduct() {
    println("Digite o nome do produto:")
    val nome = readln()

    try {
        println("Digite a quantidade:")
        val quantidade = readln().toInt()
        validaQuantidade(quantidade)
        estoque.add(Item(estoque.size, nome, quantidade))
        println("Produto adicionado")
    } catch (e: ExcecaoEstoque) {
        println(e.localizedMessage)
        main()
    } catch (e: Exception) {
        println("Valor não é válido")
        main()
    }
    main()
}

fun editProduct() {
    if (estoque.isEmpty()) {
        println("Sem produtos no estoque")
    } else {
        println("Informe o ID do produto que deseja editar:")
        val id = readln().toInt()
        if (id > estoque.size) {
            println("ID não encontrado")
        } else {
            val idProduto = id - 1
            println("Informe novo nome para o produto")
            val novoNome = readln()
            estoque[idProduto].nome = novoNome
            println("Informe nova quantidade para o produto")
            val novaQuantidade = readln().toInt()
            estoque[idProduto].quantidade = novaQuantidade
            println(
                """
                    
                #####################################
                Edição feita com sucesso!
                
                Novo nome: ${estoque[idProduto].nome}
                Nova quantidade: ${estoque[idProduto].quantidade}
                #####################################
                
            """.trimIndent()
            )
        }
    }
    main()
}

fun showInventory() {
    println("Lista dos produtos em estoque:")
    if (estoque.size != 0) {
        estoque.forEach {
            println(
                """
                
                Nome: ${it.nome} Quantidade: ${it.quantidade}
            
                """.trimIndent()
            )
        }
    } else {
        println("Não há produtos a serem exibidos")
    }
    main()
}

fun showTotals() {
    println("Lista de todos os produtos:")
    if (estoque.size != 0) {
        estoque.forEach {
            println(
                """
                    
            ID:#${"%04d".format(it.id)} Nome: ${it.nome} Quantidade: ${it.quantidade}
            
        """.trimIndent()
            )
        }
    } else {
        println("Não há produtos a serem exibidos")
    }
    main()
}

fun endingApp() {
    println("Deseja mesmo sair do sistema? (S/N)")
    when (readln().uppercase()) {
        "S" -> return
        "N" -> main()
    }
}

fun validaQuantidade(qtd: Int) {
    if (qtd > 999 || qtd < 0) {
        throw ExcecaoEstoque()
    }
}

class ExcecaoEstoque : Exception() {
    override fun getLocalizedMessage(): String {
        return "Quantidade é inválida. O mínimo é => 0 && o máximo é => 999"
    }
} // copiei da internet. Estudar novamente mais tarde. Não sei fazer.
