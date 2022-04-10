<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:gml="http://www.opengis.net/gml/3.2"
        xmlns:se="http://www.opengis.net/se"
        xmlns:xs="http://www.w3.org/2001/XMLSchema"
        xmlns:opera="http://koop.overheid.nl/apps/opera/"
        version="3.0">
  <xsl:param name="gio-doc" as="document-node()"/>
  <xsl:template match="/">
    <KMGeometrie xmlns="https://standaarden.overheid.nl/kaartmodule/stop/" xmlns:geo="https://standaarden.overheid.nl/stop/imop/geo/">
      <xsl:attribute name="schemaversie" select="/geo:GeoInformatieObjectVaststelling/@schemaversie" />
      <xsl:apply-templates select="/geo:GeoInformatieObjectVaststelling/geo:vastgesteldeVersie/geo:GeoInformatieObjectVersie"/>
      <xsl:apply-templates select="$gio-doc//se:FeatureTypeStyle"/>
    </KMGeometrie>
  </xsl:template>
  <xsl:template match="*[@srsName='urn:ogc:def:crs:EPSG::4258']">
    <xsl:copy>
      <xsl:attribute name="srsName">urn:ogc:def:crs:EPSG::28992</xsl:attribute>
      <xsl:apply-templates/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="gml:posList[ancestor::*[@srsName='urn:ogc:def:crs:EPSG::4258']]">
    <xsl:variable name="coordinates" select="tokenize(normalize-space(text()),' ')"/>
    <xsl:copy>
      <xsl:for-each select="$coordinates" >
        <xsl:variable name="current" select="position()"/>
        <xsl:if test="$current mod 2 = 0">
          <xsl:variable
                  name="etrs89-coords"
                  select="opera:etrs2RD($coordinates[$current - 1], $coordinates[$current])"
                  as="xs:double+"/>
          <xsl:value-of select="$etrs89-coords"/>&#160;
        </xsl:if>
      </xsl:for-each>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="*|@*">
    <xsl:copy>
      <xsl:apply-templates select="@*"/>
      <xsl:apply-templates/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>