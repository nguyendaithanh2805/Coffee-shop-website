package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcArticleRepository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class ArticleRowMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();
            article.setArticleId(rs.getInt("articleId"));
            article.setUserId(rs.getInt("userId"));
            article.setName(rs.getString("name"));
            article.setImage(rs.getString("image"));
            article.setDescription(rs.getString("description"));
            article.setStatus(rs.getBoolean("status"));
            return article;
        }
    }

    @Override
    public int save(Article article) {
        return jdbcTemplate.update("INSERT INTO tbl_article (userId, name, image, description, status) VALUES (?, ?, ? ,?, ?)",
                article.getUserId(), article.getName(), article.getImage(), article.getDescription(), article.getStatus());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM tbl_article WHERE articleId = ?", id);
    }

    @Override
    public void update(Article article) {
        jdbcTemplate.update("UPDATE tbl_article SET userId = ?, name = ?, image = ?, description = ?, status = ? WHERE articleId = ?",
                article.getUserId(), article.getName(), article.getImage(), article.getDescription(), article.getStatus(), article.getArticleId());
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_article", new ArticleRowMapper());
    }

    @Override
    public Article findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_article WHERE articleId = ?", new ArticleRowMapper(), id);
    }
}
