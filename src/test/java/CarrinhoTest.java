import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarrinhoTest {

    private Carrinho criarCarrinho() {
        return new Carrinho();
    }

    private Produto criarProduto() {
        return new Produto("Arroz", 10.0, 10);
    }

    @Test
    void carrinhoVazioTemTotalZero() {
        Carrinho carrinho = criarCarrinho();

        assertEquals(0.0, carrinho.calcularTotal());
    }

    @Test
    void adicionarItemAumentaTotal() {
        Carrinho carrinho = criarCarrinho();
        Produto produto = criarProduto();

        carrinho.adicionarItem(produto, 2);

        assertEquals(20.0, carrinho.calcularTotal());
    }

    @Test
    void naoPodeAdicionarQuantidadeMaiorQueEstoque() {
        Carrinho carrinho = criarCarrinho();
        Produto produto = new Produto("Arroz", 10.0, 5);

        assertThrows(
                EstoqueInsuficienteException.class,
                () -> carrinho.adicionarItem(produto, 6)
        );
    }

    @Test
    void removerItemReduzTotal() {
        Carrinho carrinho = criarCarrinho();
        Produto produto = criarProduto();

        carrinho.adicionarItem(produto, 2);
        carrinho.removerItem(produto, 1);

        assertEquals(10.0, carrinho.calcularTotal());
    }

    @Test
    void cupomValidoReduzTotal() {
        Carrinho carrinho = criarCarrinho();
        Produto produto = criarProduto();

        carrinho.adicionarItem(produto, 2);
        carrinho.aplicarCupom("DESCONTO10");

        assertEquals(18.0, carrinho.calcularTotal());
    }

    @Test
    void naoPodeAplicarMesmoCupomDuasVezes() {
        Carrinho carrinho = criarCarrinho();

        carrinho.aplicarCupom("DESCONTO10");

        assertThrows(
                CupomJaAplicadoException.class,
                () -> carrinho.aplicarCupom("DESCONTO10")
        );
    }
}