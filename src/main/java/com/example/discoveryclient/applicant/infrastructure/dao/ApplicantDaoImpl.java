package com.example.discoveryclient.applicant.infrastructure.dao;

import com.example.discoveryclient.applicant.infrastructure.Applicant;
import com.example.discoveryclient.application.Application;
import com.example.discoveryclient.joboffer.JobOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantDaoImpl implements ApplicantDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Applicant> applicantRowMapper = (resultSet, rowNum) -> new Applicant(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getString("skills")
    );

    @Override
    public List<Applicant> findAll() {
        return jdbcTemplate.query("SELECT a.*, app.* FROM applicant a LEFT JOIN application app ON a.id = app.applicant_id", applicantRowMapper);
    }

    @Override
    public Optional<Applicant> findById(Long id) {
        return jdbcTemplate.query(
                "SELECT a.*, app.* FROM applicant a LEFT JOIN application app ON a.id = app.applicant_id WHERE a.id = ?",
                new ApplicantWithApplicationsRowMapper(),
                id
        ).stream().findFirst();
    }

    @Override
    public Applicant save(Applicant applicant) {
        jdbcTemplate.update("INSERT INTO applicant (name, skills) VALUES (?, ?)",
                applicant.getName(), applicant.getSkills());
        return applicant;
    }

    @Override
    public void update(Applicant applicant) {
        jdbcTemplate.update("UPDATE applicant SET name = ?, skills = ? WHERE id = ?",
                applicant.getName(), applicant.getSkills(), applicant.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM applicant WHERE id = ?", id);
    }


    private static class ApplicantWithApplicationsRowMapper implements RowMapper<Applicant> {

        @Override
        public Applicant mapRow(ResultSet rs, int rowNum) throws SQLException {
            long currentApplicantId = rs.getLong("applicant_id");
            String name = rs.getString("name");
            String skills = rs.getString("skills");

            Applicant applicant = new Applicant(currentApplicantId, name, skills, new HashSet<>());

            do {
                long applicationId = rs.getLong("app.id"); // Replace "app.id" with actual column name in the 'application' table
                if (!rs.wasNull()) {
                    // Create JobOffer object (assuming you have a no-arg constructor and setters)
                    JobOffer jobOffer = new JobOffer();
                    jobOffer.setId(rs.getLong("job_offer_id"));

                    // Create Application object
                    Application application = new Application();
                    application.setId(applicationId);
                    application.setJobOffer(jobOffer);
                    application.setApplicant(applicant); // Set the current applicant
                    application.setStatus(rs.getString("status"));
                    application.setApplicationDate(rs.getTimestamp("application_date"));

                    applicant.getApplications().add(application);
                }
            } while (rs.next() && rs.getLong("applicant_id") == currentApplicantId);

            return applicant;
        }
    }
}
