<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Patient</th>
      <th>Email</th>
      <xsl:for-each select="Doctor">
            <tr>
            <td><i><xsl:value-of select="Name" /></i></td>
            <td><xsl:value-of select="Email" /></td>
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>
