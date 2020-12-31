package org.example.controller;

import org.example.PostService;
import org.example.domain.entity.Post;
import org.example.domain.entity.PostCategory;
import org.example.domain.entity.PostTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final PostService service;

    @Autowired
    public HomeController(PostService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(PostTransfer postTransfer, Model model) throws SQLException {
        PostTransfer postTransfer1 = service.findPostDatabase(postTransfer);
        List<Post> postList = postTransfer1.getPostList();
        List<Integer> usingPostCategory = service.selectDistinctPostCategoryId();
        for (int i = 0; i < postList.size(); i++) {
            Post post = postList.get(i);
            model.addAttribute("id" + (i + 1), post.getId());
            model.addAttribute("postName" + (i + 1), post.getPostName());
            model.addAttribute("postTheme" + (i + 1), post.getPostTheme());
            model.addAttribute("publicationDate" + (i + 1), post.getPublicationDate());
            model.addAttribute("postAuthor" + (i + 1), post.getPostAuthor());
        }
        List<PostCategory> postCategoryList = service.selectUsingPostCategories(usingPostCategory);
        model.addAttribute("categoryPost", postCategoryList);
        model.addAttribute("startPosition", postTransfer1.getStartPosition());
        model.addAttribute("postPosition", postTransfer1.getPostPosition());
        model.addAttribute("countLines", postTransfer1.getCountLines());
        return "home";
    }

}