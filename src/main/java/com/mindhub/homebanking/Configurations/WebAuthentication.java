package com.mindhub.homebanking.Configurations;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//nos permite dejar de usas la configuracion de spring segurity y poder empezar a utilizar como archivo de configuracion una clase.
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter { //es el objeto que utiliza spring segurity para saber como buscar detalles de usuario
    //WebAuthentication se extiende de GlobalAuthenticationConfigurerAdapter para poder sobrescribir el método su método init
    @Autowired
    ClientRepository clientRepository;


    @Override //authenticationManagerBuilder es el que maneja la contruccion de las autenticaciones.
    public void init(AuthenticationManagerBuilder auth) throws Exception { //retorna excepcion en caso que ocurra algo.
        auth.userDetailsService(inputName -> { //UserDetailsService es una interface de spring segurity Se usa para recuperar datos relacionados con el Usuario.
            Client client = clientRepository.findByEmail(inputName);

            if (client != null) {
                if(client.getEmail().equals("admin@gmail.com")){
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }
                else {
                    return new User(client.getEmail(), client.getPassword(), //al tener estos datos sabes que existe le damos un token/session id y un rol
                            AuthorityUtils.createAuthorityList("CLIENT"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
