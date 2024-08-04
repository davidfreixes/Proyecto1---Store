package shop.model;

import java.util.Objects;

public class Purchase {
    private long idPurchase;
    private Client client;
    private Product product;
    private int quantity;

    public Purchase(long idPurchase, Client client, Product product, int quantity) {
        this.idPurchase = idPurchase;
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     *
     * @return idPurchase
     */
    public long getIdPurchase() {
        return idPurchase;
    }

    /**
     *
     * @param idPurchase
     */
    public void setIdPurchase(long idPurchase) {
        this.idPurchase = idPurchase;
    }

    /**
     *
     * @return client
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase compra = (Purchase) o;
        return idPurchase == compra.idPurchase;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPurchase);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idPurchase=" + idPurchase +
                ", client=" + client +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
