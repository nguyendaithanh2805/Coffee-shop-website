package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcOrderDetailRepository implements OrderDetailRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderDetailRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private final static class OrderDetailRowMapper implements RowMapper<OrderDetail> {
        @Override
        public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(rs.getInt("productId"));
            orderDetail.setOrderId(rs.getInt("orderId"));
            orderDetail.setDiscount(rs.getDouble("discount"));
            orderDetail.setOrderQuantity(rs.getInt("orderQuantity"));
            orderDetail.setTotalBill(rs.getDouble("totalBill"));
            return orderDetail;
        }
    }

    @Override
    public List<OrderDetail> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_order_detail", new OrderDetailRowMapper());
    }

    @Override
    public int save(OrderDetail orderDetail) {
        return jdbcTemplate.update("INSERT INTO tbl_order_detail(productId, orderId, discount, orderQuantity, totalBill) VALUES (?, ?, ?, ?, ?)",
                orderDetail.getProductId(), orderDetail.getOrderId(), orderDetail.getDiscount(), orderDetail.getOrderQuantity(), orderDetail.getTotalBill());
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }
}
