package com.stylegenerator.plugin.util

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
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

fun Document.addElement(elementName: String, append: Boolean = true): Element {
    return createElement(elementName).also {
        if (append) {
            appendChild(it)
        }
    }
}

fun Element.addElement(doc: Document, elementName: String, append: Boolean = false): Element {
    return doc.createElement(elementName).also {
        if (append) {
            appendChild(it)
        }
    }
}

fun Element.addElement(child: Node): Element {
    appendChild(child)
    return this
}

fun Element.addComment(doc: Document, comment: String): Element {
    appendChild(doc.createComment(comment))
    return this
}

fun Element.addLineBreak(doc: Document): Element {
    appendChild(doc.createTextNode("\n\n    "))
    return this
}

fun Element.addAttribute(name: String, value: String): Element {
    setAttribute(name, value)
    return this
}
