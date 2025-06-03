package dev.destiny.afrorusbackend.repository;

import dev.destiny.afrorusbackend.model.Space;
import dev.destiny.afrorusbackend.model.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceRepo extends JpaRepository<Space, String> {
    List<Space> findSpacesByType(SpaceType type);
}
