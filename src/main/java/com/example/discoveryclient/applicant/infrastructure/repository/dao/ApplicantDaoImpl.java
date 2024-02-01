package com.example.discoveryclient.applicant.infrastructure.repository.dao;

import com.example.discoveryclient.applicant.infrastructure.entity.Applicant;
import com.example.discoveryclient.application.Application;
import com.example.discoveryclient.joboffer.JobOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantDaoImpl implements ApplicantDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Page<Applicant> findAll(Pageable pageable) {
        String sql = "SELECT a.id as a_id, name, skills, app.id as application_id, job_offer_id, applicant_id, status, application_date FROM applicant a LEFT JOIN application app ON a.id = app.applicant_id LIMIT ? OFFSET ?";
        List<Applicant> applicants = jdbcTemplate.query(sql, new ApplicantWithApplicationsRowMapper(), pageable.getPageSize(), pageable.getOffset());
        long total = countTotalApplicants();

        return new PageImpl<>(applicants, pageable, total);
    }

    private long countTotalApplicants() {
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM applicant", Long.class);
        return Optional.ofNullable(count).orElse(0L);
    }

    @Override
    public Optional<Applicant> findById(Long id) {
        return jdbcTemplate.query(
                "SELECT a.id as a_id, name, skills, app.id as application_id, job_offer_id, applicant_id, status, application_date FROM applicant a LEFT JOIN application app ON a.id = app.applicant_id WHERE a.id = ?",
                new ApplicantWithApplicationsRowMapper(),
                id
        ).stream().findFirst();
    }

    @Override
    public Applicant save(Applicant applicant) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO applicant (name, skills) VALUES (?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, applicant.getName());
            ps.setString(2, applicant.getSkills());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            applicant.setId(keyHolder.getKey().longValue());
        }

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
                long applicationId = rs.getLong("a_id");
                if (!rs.wasNull()) {
                    JobOffer jobOffer = new JobOffer();
                    jobOffer.setId(rs.getLong("job_offer_id"));

                    Application application = new Application();
                    application.setId(applicationId);
                    application.setJobOffer(jobOffer);
                    application.setApplicant(applicant);
                    application.setStatus(rs.getString("status"));
                    application.setApplicationDate(rs.getTimestamp("application_date"));

                    applicant.getApplications().add(application);
                }
            } while (rs.next() && rs.getLong("application_id") == currentApplicantId);

            return applicant;
        }
    }
}
