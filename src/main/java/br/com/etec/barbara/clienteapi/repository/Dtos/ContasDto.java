package br.com.etec.barbara.clienteapi.repository.Dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContasDto {

    private Integer id;
    private LocalDate data;
    private BigDecimal valor;
    private String nomecliente;

    public ContasDto(Integer id, LocalDate data, BigDecimal valor, String nomecliente) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.nomecliente = nomecliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }
}
