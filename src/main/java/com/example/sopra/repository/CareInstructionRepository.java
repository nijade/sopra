package com.example.sopra.repository;

import com.example.sopra.entity.CareInstruction;
import com.example.sopra.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareInstructionRepository extends JpaRepository<CareInstruction, String> {

    public CareInstruction findCareInstructionByTagTitle(String title);
}
