package com.hello.FirstApp.modals;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "user")
//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(
//                name = "do_login",
//                procedureName = "login",
//                resultClasses = { User.class },
//                parameters = {
//                        @StoredProcedureParameter( name = " in_username",  type = String.class,  mode = ParameterMode.IN),
//                        @StoredProcedureParameter( name = "in_password",  type = String.class,  mode = ParameterMode.IN),
//                        @StoredProcedureParameter( name = "out_code",  type = Integer.class,  mode = ParameterMode.OUT),
//                        @StoredProcedureParameter( name = "out_message",  type = String.class,  mode = ParameterMode.OUT)
//
//                }),
//        CREATE PROCEDURE login(IN  in_username    varchar(100),
//        IN  in_password   varchar(100),
//        OUT out_code      INT,
//        OUT out_message   varchar(100))
//        BEGIN
//        DECLARE un VARCHAR(25)
//        DECLARE pass VARCHAR(25)
//        IF in_username IS NULL OR in_username = ''
//        THEN
//        SET out_code = 1;
//        SET out_message = 'Please Enter Your Username';
//        END IF;
//        IF in_password IS NULL OR in_password = ''
//        THEN
//        SET out_code = 1;
//        SET out_message = 'Please Enter Your Password';
//        END IF;
//        SELECT un=username, pass=password FROM user WHERE username=in_username
//        IF pass == in_password
//        THEN
//        SET out_code = 1;
//        SET out_message = 'Login failed!'
//        ELSE
//        SET out_code = 1;
//        SET out_message = 'Login failed!'
//        END IF
//        /*Logi Here*/
//
//        END;

//})
public class User {
    @Id
    @GeneratedValue
    protected Integer id;
    @NotNull
    protected String username;
    protected String type;
    protected String email;
    protected String password;


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Item> items;

    public User() {
    }

    public User(Integer id, String username, String type, String email) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
