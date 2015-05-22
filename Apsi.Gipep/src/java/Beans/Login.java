package Beans;

import Dao.UsuarioDao;
import Dao.UsuarioImple;
import Entity.Usuario;
import java.awt.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Login {

    private Usuario usuario;

    public Login() {

    }

    public void EntrarSecion(ActionEvent actionEvent) {
       Usuario usua=new UsuarioImple().Loguin(usuario);
        if(usua!=null){
            System.out.println("Entro");
        }else{
            System.out.println("NaN");
        }
    }

    public Usuario getUsuario() {
        if(this.usuario==null){
            this.usuario=new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
