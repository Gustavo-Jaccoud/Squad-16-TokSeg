package com.tokseg.storage.repositories;


import com.tokseg.storage.domain.block.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BlockRepository extends JpaRepository<Block, UUID> {

}
