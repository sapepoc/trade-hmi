<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes" indent="no"
		media-type="string" encoding="UTF-8" />
<xsl:template match="Payload">{<xsl:apply-templates select="trade" />}</xsl:template>
<xsl:template match="trade">user:<xsl:value-of select="user" />,security:<xsl:value-of select="security" />,amount:<xsl:value-of select="amount" />,date:<xsl:value-of select="date" />
</xsl:template>
</xsl:stylesheet>