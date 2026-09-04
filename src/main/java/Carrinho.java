import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<Produto> produtos = new ArrayList<>();

    private double desconto = 0.0;
    private String cupomAplicado;

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

    public void aplicarCupom(String cupom) {

        if ("DESCONTO10".equals(cupom)) {

            if (cupom.equals(cupomAplicado)) {
                throw new CupomJaAplicadoException(
                        "Cupom já aplicado"
                );
            }

            cupomAplicado = cupom;
            desconto = 0.10;
        }
    }

    public double calcularTotal() {

        double total = 0.0;

        for (Produto produto : produtos) {
            total += produto.getPreco();
        }

        return total - (total * desconto);
    }

    public void finalizar() {

        if (produtos.isEmpty()) {
            throw new CarrinhoVazioException(
                    "Não é possível finalizar um carrinho vazio"
            );
        }
    }
}