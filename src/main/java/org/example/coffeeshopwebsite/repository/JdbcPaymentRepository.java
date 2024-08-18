package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcPaymentRepository implements PaymentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class PaymentRowMapper implements RowMapper<Payment> {

        @Override
        public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payment payment = new Payment();
            payment.setPaymentId(rs.getInt("paymentId"));
            payment.setName(rs.getString("name"));
            return payment;
        }
    }
    @Override
    public List<Payment> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_payment", new PaymentRowMapper());
    }
}
