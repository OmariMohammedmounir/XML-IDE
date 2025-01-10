package application;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.transform.JDOMResult;
import org.jdom2.transform.JDOMSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.StringReader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import java.io.*;

import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextArea textArea;

    private Stage stage;
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser
                .getExtensionFilters()
                .addAll(
                        new FileChooser.ExtensionFilter("Text", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    public void init(Stage myStage) {
        this.stage = myStage;
    }

    @FXML
    public void exit() {
        if (textArea.getText().isEmpty()) {
            Platform.exit();
            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Exit without saving?",
                ButtonType.YES,
                ButtonType.NO,
                ButtonType.CANCEL
        );

        alert.setTitle("Confirm");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        if (alert.getResult() == ButtonType.NO) {
            save();
            Platform.exit();
        }
    }

    @FXML
    private void save() {
        try {
            fileChooser.setTitle("Save As");
            fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("XML", "*.xml"),
	                new FileChooser.ExtensionFilter("DTD", "*.xml"),
	                new FileChooser.ExtensionFilter("XSLT", "*.xml")); 
            File file = fileChooser.showSaveDialog(stage);
            
            if (file != null) {
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(textArea.getText());
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openFile() {
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            textArea.clear();
            readText(file);
        }
    }



    private void readText(File file) {
        String text;

        try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
            while ((text = buffReader.readLine()) != null) {
                textArea.appendText(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    @FXML
    public void newFile() {
        textArea.clear();
    }

    

    

    @FXML
    public void fontSize(ActionEvent e) {
        String choice = ((CheckMenuItem) e.getSource()).getId();

        switch (choice) {
            case "small":
                textArea.setStyle("-fx-font-size: 14px");
                break;
            case "default":
                textArea.setStyle("-fx-font-size: 22px");
                break;
            case "large":
                textArea.setStyle("-fx-font-size: 30px");
                break;
            default:
                textArea.setStyle("-fx-font-size: 22px");
        }
    }
    @FXML
    private void verify()  {
        SAXBuilder builder = new SAXBuilder();
        try {
            String xmlText = textArea.getText();
            Document document = builder.build(new StringReader(xmlText));
            JOptionPane.showMessageDialog(null, "XML is well-formed");
        } catch (JDOMException | IOException e) {
            JOptionPane.showMessageDialog(null, "XML is NOT well-formed: " + e.getMessage());
        }
    }
    @FXML
    private void validatexs() {
    	try {
    		 fileChooser.setTitle("Open The xsl File");
    	        File schemaFile = fileChooser.showOpenDialog(stage);
    		String xml = textArea.getText();
    		Source xmlFile = new StreamSource(new StringReader(xml));
    	    //TODO replace with actual file location of the XML Schema  
    	    SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
    	    Schema schema = schemaFactory.newSchema(schemaFile);
    	    Validator validator = schema.newValidator();
    	    validator.validate(xmlFile);
    	    JOptionPane.showMessageDialog(null, "XML is valid against the provided XML Schema");
    	} catch (Exception e) {
    	    JOptionPane.showMessageDialog(null, "XML is NOT valid against the provided XML Schema");
    	    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
    	}
    }

    @FXML
    private void validatedtdi() {
    	
    	try {
         
            SAXBuilder builder = new SAXBuilder();
            builder.setValidation(true);
            builder.setFeature("http://xml.org/sax/features/validation", true);
            builder.setFeature("http://xml.org/sax/features/namespaces", true);
            builder.setFeature("http://xml.org/sax/features/string-interning", true);
            builder.setFeature("http://apache.org/xml/features/validation/schema", false);
            builder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            
            Document doc = builder.build(new StringReader(textArea.getText()));
            JOptionPane.showMessageDialog(null, "XML file is valid.");
        } catch (JDOMException e) {
            JOptionPane.showMessageDialog(null, "XML file is not valid.\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "I/O error: " + e.getMessage());
        }}
    @FXML
    private void xsltohtml() {
    	 try {
    	        FileChooser fileChooser = new FileChooser();
    	        fileChooser.getExtensionFilters().addAll(
    	                new FileChooser.ExtensionFilter("XML", "*.xml"),
    	                new FileChooser.ExtensionFilter("All Files", "*.*")
    	        );
    	        File xmlFile = fileChooser.showOpenDialog(stage);
    	        if (xmlFile == null) {
    	            return;
    	        }
 // Read the XSLT file into a StreamSource
    	        Source xsltSource = new StreamSource(new StringReader(textArea.getText()));

    	        // Read the XML file into a StreamSource
    	        Source xmlSource = new StreamSource(xmlFile);

    	        // Create the TransformerFactory
    	        TransformerFactory factory = TransformerFactory.newInstance();

    	        // Use the factory to create a Transformer from the XSLT file
    	        Transformer transformer = factory.newTransformer(xsltSource);

    	        // Create a StreamResult to hold the generated HTML
    	        StreamResult htmlResult = new StreamResult(new File("C:\\Users\\Bsi\\Desktop\\index.html"));

    	        // Use the transformer to transform the XML into HTML
    	        transformer.transform(xmlSource, htmlResult);

    	        Alert alert = new Alert(Alert.AlertType.INFORMATION, "HTML file generated successfully!");
    	        alert.showAndWait();
    	    } catch (TransformerException ex) {
    	        Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while generating the HTML file");
    	        alert.showAndWait();
    	    }
    	
    }
    @FXML
    private void validate() throws JDOMException, IOException {
    	try {
    		File file = fileChooser.showOpenDialog(stage);
            // Create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
            // Load a WXS schema, represented by a Schema instance
            Source schemaFile = new StreamSource(file);
            Schema schema = factory.newSchema(schemaFile);
            
            // Create a Validator object, which can be used to validate an instance document
            Validator validator = schema.newValidator();
            
            // Validate the XML file against the schema
            Source source = new StreamSource(new StringReader(textArea.getText()));
            validator.validate(source);
            
            JOptionPane.showMessageDialog(null, "XML file is valid.");
        } catch (SAXException e) {
            JOptionPane.showMessageDialog(null, "XML file is not valid.\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "I/O error: " + e.getMessage());
        }
    }
    	
    		
    
    
    @FXML
    private void SearchElement() throws ParserConfigurationException{
    	 try {
    	        // Create a DOM document from the text in the text area
    	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	        DocumentBuilder builder = factory.newDocumentBuilder();
    	        InputSource inputSource = new InputSource(new StringReader(textArea.getText()));
    	        org.w3c.dom.Document document = builder.parse(inputSource);

    	        // Search for the element with the given name
    	        String elementName = JOptionPane.showInputDialog("Enter element name:");
    	        NodeList nodeList = document.getElementsByTagName(elementName);

    	        // Show the results
    	        if (nodeList.getLength() == 0) {
    	            JOptionPane.showMessageDialog(null, "Element not found");
    	        } else {
    	            String result = "Elements found: " + nodeList.getLength() + "\n\n";
    	            for (int i = 0; i < nodeList.getLength(); i++) {
    	                result += nodeList.item(i).getTextContent() + "\n";
    	            }
    	            JOptionPane.showMessageDialog(null, result);
    	        }
    	    } catch (ParserConfigurationException | SAXException | IOException ex) {
    	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    	        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
    	    }
    	
    }
    @FXML
    private void SearchAttribute() throws ParserConfigurationException{
    	 String xmlText = textArea.getText();
    	    try {
    	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	        InputSource inputSource = new InputSource(new StringReader(xmlText));
    	        org.w3c.dom.Document doc = dBuilder.parse(inputSource);
    	        doc.getDocumentElement().normalize();

    	        String attributeName = JOptionPane.showInputDialog("Enter attribute name:");
    	        if (attributeName == null || attributeName.trim().isEmpty()) {
    	            return;
    	        }

    	        NodeList nodeList = doc.getElementsByTagName("*");
    	        StringBuilder results = new StringBuilder();
    	        for (int i = 0; i < nodeList.getLength(); i++) {
    	            Node node = nodeList.item(i);
    	            if (node.getNodeType() == Node.ELEMENT_NODE) {
    	                NamedNodeMap attributes = node.getAttributes();
    	                for (int j = 0; j < attributes.getLength(); j++) {
    	                    Node attribute = attributes.item(j);
    	                    if (attribute.getNodeName().equals(attributeName)) {
    	                        results.append("Attribute ")
    	                                .append(attributeName)
    	                                .append(" found in element ")
    	                                .append(node.getNodeName())
    	                                .append(" with value ")
    	                                .append(attribute.getNodeValue())
    	                                .append("\n");
    	                    }
    	                }
    	            }
    	        }

    	        if (results.length() > 0) {
    	            JOptionPane.showMessageDialog(null, results.toString());
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Attribute not found");
    	        }
    	    } catch (ParserConfigurationException | SAXException | IOException e) {
    	        JOptionPane.showMessageDialog(null, "Invalid XML");
    	    }}
}
