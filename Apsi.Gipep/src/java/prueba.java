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

import Dao.UsuarioImple;
import Entity.Persona;
import Entity.Usuario;
import Dao.PersonaHelper;
import java.math.BigDecimal;

/**
 *
 * @author usuario
 */
public class prueba {

    /**
     * @param args
     */
    public static void main(String[] args) {
        UsuarioImple MiUser = new UsuarioImple();
        PersonaHelper myPeople = new PersonaHelper();
        Usuario u = new Usuario(BigDecimal.valueOf(MiUser.cargarTodo().size() + 1), "123", "Probe");
        Persona p = new Persona(BigDecimal.valueOf(Integer.parseInt("12123")),
                u, "Probwe", "Cosa");
        try {
            MiUser.CrearUsuario(u);
            myPeople.agregar(p);
            System.out.println("ok");       
        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
            System.out.println(e.getMessage());
        }
    }
}
