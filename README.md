# Rebatcher

## Overview

**Rebatcher** is a simple yet effective Java application designed to help users rename multiple files in a selected directory. It allows you to set a base name for the files, specify a starting index, and choose whether the index appears at the beginning or the end of the file name. This tool is perfect for organizing files with consistent naming conventions.

## Features

- **Directory Selection**: Choose the directory containing the files you want to rename.
- **Base Name Input**: Specify a base name for the new file names.
- **Starting Index**: Set a starting index from which to begin renaming files.
- **Index Positioning**: Choose whether to place the index at the start or end of the file names.
- **User-Friendly GUI**: Easy-to-use graphical interface built with Java Swing.
- **File Renaming**: Renames all files in the selected directory based on your specifications.

## Prerequisites

Before running Rebatcher, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Maven** (optional): If you want to build the project from source.

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/rebatcher.git
   cd rebatcher
   ```
    Compile and run the application:

    If you have Maven installed, you can build the project using:
```bash   
    mvn package
```    
After building, navigate to the target directory and run the JAR file:

```bash
java -jar target/rebatcher-1.0-SNAPSHOT.jar
```
If you do not have Maven, you can compile the Java files using:

```bash
javac Main.java
```
And then run the compiled class:

```bash
    java Main
```
## Usage

- Select a Directory: Click the "Choose Directory" button to open a file chooser dialog.
- Select the directory containing the files you want to rename.
- Enter Base Name: In the "Base Name" field, enter the desired base name for the files.
- Set Start Index: Input the starting index in the "Start Index" field (default is 1).
- Choose Index Position: Select whether you want the index to appear at the start or end of the new file names.
- Rename Files: Click the "Rename Files" button to process the renaming. A success message will be displayed upon completion.

## Example

If you have the following files in your selected directory:

- file1.txt
- file2.txt
- file3.txt

And you set the Base Name to "Document", the Start Index to "1", and choose "Start" for the index position, the renamed files will be:

- 1Document.txt
- 2Document.txt
- 3Document.txt

## License

This project is licensed under the GNU General Public License v3.0.
## Contributing

Contributions are welcome! If you encounter any bugs or have suggestions for improvements, please open an issue or submit a pull request.

Enjoy organizing your files with Rebatcher!

