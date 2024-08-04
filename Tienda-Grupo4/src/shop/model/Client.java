package shop.model;

import java.util.Objects;

public class Client {
    private long clientId;
    private String name;
    private String surname;

    public Client(String name) {
        this.name = name;
    }

    public Client(long clientId, String name, String surname) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client cliente = (Client) o;
        return clientId == cliente.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clientId);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
