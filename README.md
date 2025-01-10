# XML-IDE XML Editor with Validation and Transformation (DTD / XSL / XSLT )
This project is a graphical user interface (GUI) application developed in Java using JavaFX. The application serves as an XML editor with features that allow users to create, edit, validate, and transform XML files. It is designed to assist users in working with XML documents by providing tools for schema validation, DTD validation, XSL transformations, and more.

Features
XML Editing:

A text area for users to write or edit XML content.
Support for creating new files and saving changes to disk.
File Operations:

Open and save files with a file chooser.
Supports XML, DTD, and XSLT file formats.
Validation:

Validate XML files against XSD (XML Schema Definition).
Validate XML files against DTD (Document Type Definition).
Check if the XML is well-formed.

Transformation:Transform XML documents into HTML using XSLT.

Font Size Customization:Adjust font size for better readability.

Element Search:Search for specific elements or nodes within an XML document using DOM.

Technologies Used
JavaFX: For building the user interface.
JDOM: For XML parsing and manipulation.
SAXBuilder: For validating and processing XML.
Java DOM and SAX API: For XML operations.
File I/O: For reading and writing files.
JavaFX Alerts: For user notifications.
Java Swing JOptionPane: For quick pop-up messages.

How It Works
Application Initialization:

The Main class initializes the JavaFX application and loads the FXML file to set up the GUI.
The Controller class manages all the user interactions and operations.
Core Functionalities:

New File: Clears the editor for creating a new XML document.
Open File: Reads an existing file and displays its content in the text area.
Save File: Saves the content of the text area to a specified file.
Validation Features:
Well-Formed Check: Ensures the XML is syntactically correct.
XSD Validation: Uses a selected XSD file to validate the XML document.
DTD Validation: Verifies the XML against its DTD structure.
Transformation Features:

XSLT to HTML: Converts XML into HTML using an XSLT file provided by the user.
Search Feature:

Users can search for specific elements or nodes in the XML using DOM.
Customization:

The font size of the editor can be adjusted dynamically.
Usage Instructions
Clone the repository to your local machine.
Open the project in your favorite Java IDE (e.g., IntelliJ IDEA or Eclipse).
Make sure JavaFX is configured in your environment.
Run the Main class to launch the application.
Use the menu options to perform various XML-related tasks:
Create or open an XML file.
Validate XML content.
Transform XML into HTML.
Save your work.
Future Enhancements
Add more transformations (e.g., JSON conversion).
Implement syntax highlighting for better XML readability.
Support for XPath queries for advanced element searching.



