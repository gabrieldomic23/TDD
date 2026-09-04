import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarrinhoTest {

    @Test
    void carrinhoVazioTemTotalZero() {
        Carrinho carrinho = new Carrinho();

        assertEquals(0.0, carrinho.calcularTotal());
    }

    @Test
    void adicionarItemAumentaTotal() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Arroz", 10.0, 10);

        carrinho.adicionarItem(produto, 2);

        assertEquals(20.0, carrinho.calcularTotal());
    }

    @Test
    void naoPodeAdicionarQuantidadeMaiorQueEstoque() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Arroz", 10.0, 5);

        assertThrows(
                EstoqueInsuficienteException.class,
                () -> carrinho.adicionarItem(produto, 6)
        );
    }
}