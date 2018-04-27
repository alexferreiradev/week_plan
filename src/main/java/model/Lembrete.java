package model;

public class Lembrete {

    private String id;
    private String descricao;
    private String data;
    private String estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lembrete lembrete = (Lembrete) o;

        if (id != null ? !id.equals(lembrete.id) : lembrete.id != null) return false;
        if (descricao != null ? !descricao.equals(lembrete.descricao) : lembrete.descricao != null) return false;
        if (data != null ? !data.equals(lembrete.data) : lembrete.data != null) return false;
        return estado != null ? estado.equals(lembrete.estado) : lembrete.estado == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lembrete{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
