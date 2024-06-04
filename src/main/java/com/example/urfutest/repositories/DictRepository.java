package com.example.urfutest.repositories;

import com.example.urfutest.entities.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;
import java.util.UUID;

public interface DictRepository extends JpaRepository<Dict, UUID> {

    @Query(nativeQuery = true, value = "select * from dict where parent_id =\n" +
            "(select id from dict where bcode = :bcode)")
    List<Dict> findAllByParentCode(String bcode);
}
