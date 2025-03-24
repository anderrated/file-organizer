# file-organizer

A simple **Java-based file organizer** that sorts files from a given directory into subdirectories based on their file extensions. The program is configurable through a `config.txt` file, and it supports an optional flag to specify the move behavior.

## Features

- **Sort Files**: Automatically moves files into corresponding folders in home directory based on their extensions (e.g. `.pdf` to `Documents`).
- **Configurable**: Uses a `config.txt` file to map file extensions to directory names.
- **Flexible Move Options**: Supports move flags for handling existing files (atomic move, copy attributes, replace existing).

## Arguments

The program takes **two required arguments** and **one optional argument**:

1. **Directory Path**: Path to the directory containing the files to be sorted.
2. **Config File Path**: Path to a `config.txt` file that contains mappings of file extensions to target directories.
3. **Optional Move Flag**: A flag to specify the behavior when moving files:

   - `-a`: Atomic move (files are moved as an atomic operation).
   - `-c`: Copy attributes (keeps file attributes like timestamps).
   - `-r`: Replace existing files (replaces files with the same name).

   If no move flag is provided, the program defaults to `-r` (replace existing).

### Example Config File (`config.txt`)

The `config.txt` file should contain **key-value pairs**, where the key is the file extension and the value is the corresponding target directory. Here’s is the default example:

```
docx Documents
pdf Documents
xlsx Documents
txt Documents
rtf Documents
pages Documents
webp Pictures
png Pictures
svg Pictures
jpg Pictures
jpeg Pictures
gif Pictures
mp4 Videos
webm Videos
wmv Videos
mov Videos
mpg Videos
mpeg Videos
mp3 Music
wav Music
m4a Music
w4a Music
```

## How to Use

1. **Clone the Repository**:

```bash
git clone https://github.com/anderrated/file-organizer.git
cd file-organizer
```

2. **Compile the Java Files**:

```bash
javac Orgies.java
```

3. **Run the Program**:

Use the following command to run the program, passing in the required arguments:

```bash
java Orgies /path/to/directory /path/to/config.txt [-a | -c | -r]
```

Example with default move flag (`-r`):

```bash
java Orgies /path/to/directory /path/to/config.txt
```

Example with atomic move flag (`-a`):

```bash
java Orgies /path/to/directory /path/to/config.txt -a
```

Example with copy attributes move flag (`-c`):

```bash
java Orgies /path/to/directory /path/to/config.txt -c
```

## Example Program Flow

### Config File (`config.txt`)

```
docx Documents
mp3 Music
mp4 Movies
```

### Command

```bash
java Orgies /Users/yourusername/Downloads /Users/yourusername/config.txt -r
```

### Result

1. The program scans the /Users/yourusername/Downloads directory.

2. Files like `report.docx`, `rain.mp3` and `avengers.mp4` are moved to the `~/Documents`, `~/Music` and `~/Movies` directories respectively.

3. If a file with the same name already exists, it will be replaced (since the -r flag was used).
