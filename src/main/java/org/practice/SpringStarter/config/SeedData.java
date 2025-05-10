package org.practice.SpringStarter.config;

import java.util.*;
import org.practice.SpringStarter.models.Account;
import org.practice.SpringStarter.models.Authority;
import org.practice.SpringStarter.models.Post;
import org.practice.SpringStarter.services.AccountService;
import org.practice.SpringStarter.services.AuthorityService;
import org.practice.SpringStarter.services.PostService;
import org.practice.SpringStarter.util.constants.Privillages;
import org.practice.SpringStarter.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for (Privillages auth : Privillages.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();
        Account account05 = new Account();

        account01.setEmail("account01demo@gmail.com");
        account01.setPassword("password01");
        account01.setFirstname("user01");
        account01.setLastname("LastName01");

        account02.setEmail("account02demo@gmail.com");
        account02.setPassword("password02");
        account02.setFirstname("user02");
        account02.setLastname("LastName02");

        account03.setEmail("editor@editor.com");
        account03.setPassword("pass987");
        account03.setFirstname("Editor");
        account03.setLastname("lastname");
        account03.setRole(Roles.EDITOR.getRole());

        account04.setEmail("super_editor@editor.com");
        account04.setPassword("pass987");
        account04.setFirstname("Editor");
        account04.setLastname("lastname");
        account04.setRole(Roles.EDITOR.getRole());

        account05.setEmail("admin@admin.com");
        account05.setPassword("pass987");
        account05.setFirstname("Admin");
        account05.setLastname("lastname");
        account05.setRole(Roles.ADMIN.getRole());
        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        account04.setAuthorities(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);
        accountService.save(account05);

        List<Post> posts = postService.getAll();
        if (posts.size() == 0) {
            Post post01 = new Post();
            post01.setTitle("Post 01");
            post01.setBody("Post 01 body...............");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Post 02");
            post02.setBody("Post 02 body...............");
            post02.setAccount(account02);
            postService.save(post02);
        }
    }
}
