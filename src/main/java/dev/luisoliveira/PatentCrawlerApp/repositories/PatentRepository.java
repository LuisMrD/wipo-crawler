package dev.luisoliveira.PatentCrawlerApp.repositories;

import dev.luisoliveira.PatentCrawlerApp.entities.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatentRepository extends JpaRepository<Patent, Long> {
    List<Patent> findByPublicationNumberContainingAndApplicantContaining(String PublicationNumber, String applicant);

    List<Patent> findAllByPublicationNumber(String Publication);

    @Query("SELECT p FROM Patent p WHERE p.applicant LIKE %:applicantName%")
    List<Patent> findByApplicantContaining(String applicantName);
}
