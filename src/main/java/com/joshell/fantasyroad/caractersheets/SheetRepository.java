package com.joshell.fantasyroad.caractersheets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetRepository extends JpaRepository<CharacterSheets, Long> {
    public CharacterSheets findSheetById(Long id);

}
