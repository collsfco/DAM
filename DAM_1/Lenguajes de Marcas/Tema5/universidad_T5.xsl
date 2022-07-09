<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!-- Formato de salida para la conversión del archivo xml -->
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <!-- <xsl:key name="name" match="pattern" use="expression"/> -->
    <xsl:key name="buscatitulo" match="//grado" use="@id_c"></xsl:key>
    
   <xsl:template match="/">
  <html>
    <head>
    <!-- Enlace del ccs. -->
     <link rel="stylesheet" type="text/css" href="estilos.css"/>
    </head>
    <body>
     <div>
       <h1>Grados de la Facultad de Ciencias</h1>
       <ul>
         <xsl:for-each 
            select="//grado[centro='Facultad de Ciencias']">
            <li>
              (<xsl:value-of select="@id_c"/>) <b><xsl:value-of select="nombre"/></b> -Plan: <xsl:value-of select="plan"/>
            </li>
          </xsl:for-each>
      </ul>
        <h2>Listado de Asignaturas Troncales (2ºT) de la Facultad de Ciencias</h2>
        <table>
          <thead>
            <tr>
              <th>Código</th>
              <th>Nombre</th>
              <th>Titulación</th>
              <th>Creditos Teóricos</th>
              <th>Prácticas</th>
              <th>Comentario</th>
            </tr>
          </thead>
          <tbody>
            <xsl:for-each select ="//asignatura[@titulacion=//grado[centro='Facultad de Ciencias']/@id_c and trimestre=2 and caracteristicas/tipo='Troncal']">
           <!-- Ordenadar por creditos teoricos de forma ascendente -->
            <xsl:sort select="creditos_teoricos" order="ascending"/>
              <tr>
                <td><xsl:value-of select="@id_a"/></td>
                <td><xsl:value-of select="nombre"/></td>
                <td><xsl:value-of select="key('buscatitulo',@titulacion)/nombre"/></td>
                <td><xsl:value-of select="creditos_teoricos"/></td>
                <td><xsl:value-of select="//practicas"/></td>
                <td>
                  <xsl:choose>
                    <!-- &lt; < (less than) /// &gt; > (greater than -->
                    <!-- &le;  less-than or equals sign: ≤ -->
                    <!-- &ge;  greater-than or equals sign: ≥ -->
                    <xsl:when test="creditos_teoricos &lt;2">Corta</xsl:when>
                    <xsl:when test="creditos_teoricos &gt;2 and creditos_teoricos &lt;5">Media</xsl:when>
                    <xsl:when test="creditos_teoricos &gt;5">Larga</xsl:when>
                    <xsl:otherwise>Sin información</xsl:otherwise>
                  </xsl:choose>
                </td>
                </tr>
              </xsl:for-each>
            </tbody>
        </table>
        </div>
      </body>
  </html>
  </xsl:template>
</xsl:stylesheet>
