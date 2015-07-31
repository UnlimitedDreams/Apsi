/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Entity.Usuario;


public interface UsuarioDao {
    public Usuario Loguin(Usuario usu);
    public boolean CrearUsuario(Usuario usu);
    public boolean BuscarUsuario(Usuario usu);
}
