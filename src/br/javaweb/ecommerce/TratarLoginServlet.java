package br.javaweb.ecommerce;

import br.javaweb.ecommerce.modelo.TrataLoginNegocio;
import br.javaweb.ecommerce.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TratarLoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
       
        PrintWriter out = response.getWriter();
        try {
            // joga usuario e senha nas strings
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            // instancia o objeto Usuario que vai ser usado na DAO
            Usuario objUsuario = new Usuario();
            objUsuario.setUsuario(usuario);
            objUsuario.setSenha(senha);
            
     
            TrataLoginNegocio trataLogin = new TrataLoginNegocio();

            if (trataLogin.verificaLogin(objUsuario)) {

                HttpSession sessao = request.getSession();

                sessao.setAttribute("usuarioSessao", objUsuario);

                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TratarLoginServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Usuario ok " + objUsuario.getUsuario() + "</h1>");
                out.println("<a href=\"catalogoProdutos\">Clique aqui para listar o catalogo de produtos</a>");
                out.println("</body>");
                out.println("</html>");
            } else {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TratarLoginServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> <center>Usuario ou senha incorreta </center></h1>");
                out.println("</body>");
                out.println("</html>");
            }

        } finally {
            out.close();
        }
    }
}
