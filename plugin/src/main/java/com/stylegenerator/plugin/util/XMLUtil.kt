package com.stylegenerator.plugin.util

import org.w3c.dom.Document
import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import java.util.HashMap
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

object XMLUtil {

    @Throws(TransformerException::class)
    fun writeToFile(doc: Document, file: File) {
        val transformer = TransformerFactory.newInstance().newTransformer().apply {
            setOutputProperty(OutputKeys.METHOD, "xml")
            setOutputProperty(OutputKeys.ENCODING, "utf-8")
            setOutputProperty(OutputKeys.INDENT, "yes")
            setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
        }

        val source = DOMSource(doc)
        val result = StreamResult(file)
        transformer.transform(source, result)
    }

    fun readFromXML(inputFile: File, tag: String): Map<String, String> {
        val foundResources = HashMap<String, String>()

        try {
            val doc = XMLUtil.getDocument(inputFile)
            doc.documentElement.normalize()
            val stringNodes = doc.getElementsByTagName(tag)

            for (i in 0 until stringNodes.length) {
                val stringNode = stringNodes.item(i)
                foundResources[stringNode.attributes.item(0).nodeValue] = stringNode.firstChild.nodeValue
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return foundResources
    }

    @Throws(ParserConfigurationException::class)
    fun newDocument(): Document {
        val documentBuilder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
        return documentBuilder.newDocument()
    }

    @Throws(ParserConfigurationException::class, IOException::class, SAXException::class)
    fun getDocument(file: File): Document {
        val documentBuilder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
        return documentBuilder.parse(file)
    }

}
