package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Order;
import org.example.coffeeshopwebsite.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setPaymentId(rs.getInt("paymentId"));
            order.setUserId(rs.getInt("userId"));
            order.setOrderDate(rs.getDate("orderDate"));
            order.setDeliveryDate(rs.getDate("deliveryDate"));
            order.setStatus(rs.getBoolean("status"));
            order.setNote(rs.getString("note"));
            order.setAddress(rs.getString("address"));

            Payment payment = new Payment();
            payment.setPaymentId(rs.getInt("paymentId"));
            payment.setName(rs.getString("name"));
            order.setPayment(payment);
            return order;
        }
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT o.*, p.name FROM tbl_order o " +
                "JOIN tbl_payment p ON o.paymentId = p.paymentId";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }


    @Override
    public int save(Order order) {
        return jdbcTemplate.update("INSERT INTO tbl_order (paymentId, userId, orderDate, deliveryDate, status, note, address) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                order.getPaymentId(), order.getUserId(),
                order.getOrderDate(), order.getDeliveryDate(),
                order.getStatus(), order.getNote(), order.getAddress());
    }

    @Override
    public Order findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT o.*, p.name FROM tbl_order o " +
                    "JOIN tbl_payment p ON o.paymentId = p.paymentId " +
                    "WHERE o.orderId = ?", new OrderRowMapper(), id);
    }

    @Override
    public void update(Order order) {
        jdbcTemplate.update("UPDATE tbl_order SET status = ? WHERE orderId = ?", order.getStatus(), order.getOrderId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM tbl_order WHERE orderId = ?", id);
    }
}
