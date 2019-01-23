<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
 
<html>
   <head>
      <title>Eventos</title>
      <style>
        ul {
          list-style-type: none;
          margin: 0;
          padding: 0;
          overflow: hidden;
          background-color: #333333;
        }

        li {
          float: left;
        }

        li a {
          display: block;
          color: white;
          text-align: center;
          padding: 16px;
          text-decoration: none;
        }

        li a:hover {
          background-color: #111111;
        }
        </style>
   </head>
   
   <body>
      <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
         url = "jdbc:mysql://localhost:3306/sistematickets"
         user = "anni"  password = "Ros1t@123"/>
      
      <ul>
        <li><a href="micro/sesion">Iniciar sesión</a></li>
        <li><a href="micro/sesion/registroForm">Registrarse</a></li>
      </ul>
 
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from evento;
      </sql:query>
      <h1>Eventos</h1>
      <table width = "100%">
         <tr align="center">
            <th>NOMBRE</th>
            <th>TIPO</th>
            <th>LUGAR</th>
            <th>FECHA</th>
            <th>ARTISTA</th>
            <th>PRECIO</th>
            <th></th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}">
            <tr align="center">
               <td><c:out value = "${row.nombre}"/></td>
               <td><c:out value = "${row.tipo}"/></td>
               <sql:query dataSource = "${snapshot}" var = "lugs">
                    SELECT * FROM lugar where id = ?;
                    <sql:param value = "${row.lugar}" />
               </sql:query>
                    <c:forEach var = "lug" items = "${lugs.rows}">
                        <td><c:out value = "${lug.nombre}"/></td>
                    </c:forEach>
                
               <td><c:out value = "${row.fecha}"/></td>
               <td><c:out value = "${row.artista}"/></td>
               <td><c:out value = "${row.precio}"/></td>
               <td><button>VER</button></td>
            </tr>
         </c:forEach>
      </table>
 
   </body>
</html>