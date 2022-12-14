package com.mindhub.homebanking.Configurations;

import jdk.jfr.Enabled;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.jar.Attributes;

@EnableWebSecurity //Activa la seguridad teniendo en cuenta la config que le pasamos
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter{ //tendra sus propias conf de seguridad
    @Override //sobreescribimos el metodo configure para definir el metodo para autorizar a los usuarios.
    protected void configure(HttpSecurity httpSecurity) throws Exception{ //http es un objeto permite conf la web basada en peticiones http
            httpSecurity.authorizeRequests()
                    .antMatchers("/api/").permitAll()
                    .antMatchers("/rest/**").hasAuthority("ADMIN")
                    .antMatchers("/web/account.html","/web/accounts.html","web/card.html","/web/loan.html").hasAuthority("CLIENT")
                    .antMatchers("/web/index.html","/web/login.html", "/web/register").permitAll();


            httpSecurity.formLogin()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginPage("/api/login");


            httpSecurity.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");
            httpSecurity.csrf().disable();//desactiva la comprobacion de token csrf.
            httpSecurity.headers().frameOptions().disable();//Desactivamos el frameOption para poder ingresar a nuestra base de datos.
            httpSecurity.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
            //SI el usuario no esta autenticado envia una respuesta que fallo la autenticacion.
            httpSecurity.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
            //Si el usuario esta auntenticado, borra las banderas que solicitan la autenticacion.
            httpSecurity.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
            //Si el inicio de session falla, que envie una respuesta.
            httpSecurity.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
            //SI el cierre de session es exitoso,envia un mensaje de cierre exito

    } //crearAuthenticationAttributes levanta las banderas para que al cambiar de pagina no tengamos que logearnos otra vez-.
    private void clearAuthenticationAttributes(HttpServletRequest request){ //funcion de utilidad para eliminar las marcas
        HttpSession session= request.getSession(false);       //que spring establece cuando un usuario no autenticado
        if(session!=null){                                          //intenta ingresar. (Limpia las redFlags)
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}
