# XML-IDE XML Editor with Validation and Transformation (DTD / XSL / XSLT)

XML-IDE is a graphical user interface (GUI) application built in Java using JavaFX. It serves as an XML editor with features for users to create, edit, validate, and transform XML documents. This application assists in handling XML files with tools for schema validation (XSD, DTD), well-formed checks, XSL transformations, and more.

## Features ✨

### XML Editing:
- **Text Area**: Provides a space for users to write or edit XML content.
- **File Operations**: 
  - Create new XML files and save changes to disk.
  - Open existing XML, DTD, and XSLT files.
  
### Validation:
- **Well-Formed Check**: Ensures that the XML is syntactically correct.
- **XSD Validation**: Validates XML files against a selected XML Schema Definition (XSD).
- **DTD Validation**: Verifies XML files against a Document Type Definition (DTD).

### Transformation:
- **XSLT to HTML**: Transforms XML documents into HTML using a provided XSLT file.

### Additional Features:
- **Font Size Customization**: Dynamically adjust the font size for better readability.
- **Element Search**: Search for specific elements or nodes within an XML document using DOM.

## Technologies Used ⚙️

- **JavaFX**: For building the user interface.
- **JDOM**: For XML parsing and manipulation.
- **SAXBuilder**: For validating and processing XML.
- **Java DOM and SAX API**: For XML operations.
- **File I/O**: For reading and writing files.
- **JavaFX Alerts**: For user notifications.
- **Java Swing JOptionPane**: For quick pop-up messages.

## How It Works 🔧

### Application Initialization:
1. **Main Class**: Initializes the JavaFX application and loads the FXML file to set up the GUI.
2. **Controller Class**: Manages user interactions and operations, such as opening, saving, validating, and transforming files.

### Core Functionalities:
- **New File**: Clears the editor for creating a new XML document.
- **Open File**: Opens an existing XML file and displays its content in the text area.
- **Save File**: Saves the content of the text area to a specified file.

### Validation Features:
- **Well-Formed Check**: Verifies that the XML is syntactically correct.
- **XSD Validation**: Validates the XML file using a selected XSD schema.
- **DTD Validation**: Ensures the XML adheres to the DTD structure.

### Transformation Features:
- **XSLT to HTML**: Allows transformation of XML documents into HTML using an XSLT stylesheet.

### Search Feature:
- **Element Search**: Search for specific elements or nodes within the XML using DOM traversal methods.

### Customization:
- **Font Size**: Users can adjust the font size of the XML editor for a more comfortable reading experience.

## Usage Instructions 🚀

1. **Clone the Repository**:
   ```bash
   https://github.com/OmariMohammedmounir/XML-IDE.git
