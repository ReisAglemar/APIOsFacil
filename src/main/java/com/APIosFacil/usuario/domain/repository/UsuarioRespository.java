package com.APIosFacil.usuario.domain.repository;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRespository extends JpaRepository<UsuarioEntity, Long> {
    Page<UsuarioEntity> findAllByAtivoTrue(Pageable pagina);
}
