package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.ejbs.UsuarioService;
import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet {
    @EJB(lookup="java:module/mensagemService")
    private MensagemService mensagemService;

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        String operacao = request.getParameter("operM");
        PrintWriter respostaM = response.getWriter();
        switch (operacao) {
            case "1": {
                long id = Integer.parseInt(request.getParameter("id"));
                String texto = request.getParameter("texto");
                mensagemService.inserir(id, texto);
            }
            case "2": {
                for(Mensagem mensagem: mensagemService.listar()){
                    respostaM.println(mensagem.getTexto());
                }
                break;
            }
            case "3": {
                long id = Integer.parseInt(request.getParameter("id"));
                Mensagem m = mensagemService.pesquisarPorId(id);
                respostaM.println(m.getTexto());
            }
        }
    }

}







