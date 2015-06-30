
import Dao.UsuarioDao;
import Dao.UsuarioImple;
import Entity.Usuario;
import java.math.BigDecimal;

/*
 * The MIT License
 *
 * Copyright 2015 Miguel Angel Lemos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 *
 * @author usuario
 */
public class prueba {

    public static void main(String[] args) {
       
        Usuario x;
        UsuarioDao usu = new UsuarioImple();
        boolean ww = new UsuarioImple().BuscarUsuario(new Usuario(BigDecimal.valueOf(1), null, null));
        System.out.println(ww);
//        x = new Usuario(new BigDecimal(1), "juan", "juan");
//        System.out.println("Usuario: " + new UsuarioImple().Loguin(x).getUsuario());
//        System.out.println("Constraseña: " + new UsuarioImple().Loguin(x).getContrasea());
    }
}
