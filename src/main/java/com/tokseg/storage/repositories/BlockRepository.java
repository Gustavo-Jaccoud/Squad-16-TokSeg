package com.tokseg.storage.repositories;


import com.tokseg.storage.domain.block.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.hibernate.validator.constraints.UUID;

public interface BlockRepository extends JpaRepository<Block, UUID> {

}
