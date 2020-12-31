package org.example.dao;

import org.example.domain.entity.Post;
import org.example.domain.entity.PostCategory;
import org.example.domain.entity.PostTransfer;

import java.sql.SQLException;
import java.util.List;

public interface PostDao extends GenericDao<Post, Integer> {

    PostTransfer findPostDatabase(PostTransfer postTransfer) throws SQLException;

    Integer countLines() throws SQLException;

    List<PostCategory> selectUsingPostCategories(List<Integer> categoryID);

    Integer saveCategory(PostCategory postCategory);

    void deleteCategory(Integer id) throws SQLException;

    List<Integer> selectDistinctPostCategoryId();

    List<PostCategory> selectAllCategories();

    PostCategory findPostCategoryById(Integer id);

}
