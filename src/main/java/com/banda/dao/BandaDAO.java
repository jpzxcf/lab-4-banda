package com.banda.dao;

import com.banda.model.Banda;
import java.util.List;

public interface BandaDAO {

    void salvar(Banda banda);

    Banda buscarPorId(Long id);

    List<Banda> listarTodos();

    void atualizar(Banda banda);

    void remover(Long id);

    List<Banda> listarPorGenero(String genero);
}
