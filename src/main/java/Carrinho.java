import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionarItem(Produto produto, int quantidade) {

        if (quantidade > produto.getEstoque()) {
            throw new EstoqueInsuficienteException(
                    "Quantidade solicitada maior que o estoque disponível"
            );
        }

        adicionarProdutos(produto, quantidade);
    }

    public void removerItem(Produto produto, int quantidade) {
        removerProdutos(produto, quantidade);
    }

    private void adicionarProdutos(Produto produto, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            produtos.add(produto);
        }
    }

    private void removerProdutos(Produto produto, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            produtos.remove(produto);
        }
    }

    public double calcularTotal() {
        double total = 0.0;

        for (Produto produto : produtos) {
            total += produto.getPreco();
        }

        return total;
    }
}