package Beans;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Beans;
//
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.Serializable;
//import java.net.URLDecoder;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpSession;
//
//import static org.apache.catalina.connector.InputBuffer.DEFAULT_BUFFER_SIZE;
//
///**
// *
// * @author Microinformatica
// */
//@ManagedBean
//@SessionScoped
//public class ReportesBeans implements Serializable {
//
//    /**
//     * // * Creates a new instance of Reportes
//     */
//    public ReportesBeans() {
//    }
//
//    public void reporteUsuarios() {
//        try {
//          Connection con;
//          con=DriverManager.getConnection("jdbc:postgresql://localhost:5432;databaseName=Tesis");
//          JasperReport Jasp=null;
//          Jasp=(JasperReport)JRLoader.loadObjectFromFile("jdbc:postgresql://localhost:5432;databaseName=Tesis//report5.jasper");
//          JasperPrint print= JasperFillManager.fillReport(Jasp, null,con);
//          JasperViewer ver=new JasperViewer(print);
//          ver.setTitle("usuarios");
//          ver.setVisible(true);
//        } catch (Exception ex) {
//            System.out.println("ERROR ->" + ex.getMessage());
//        }
//    }
//   
//}
