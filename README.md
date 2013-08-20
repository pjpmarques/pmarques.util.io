pmarques.util.io
================

Utility package for performing several IO operations.
Currently it supports an efficient implementation of Java's BufferedReader that allows to read lines specifying a maxinum number of characters to read.

Example of usage:

    import pmarques.util.io.BoundedBufferedReader
    ...
    BoundedBufferedReader br = new BoundedBufferedReader(new FileReader("myFile.txt"));
    
    // Read a line upto 1024 characters
    String line = br.readLine(1024);



Installation and Running
========================

The project is built and run using maven

1. Make sure that you have Maven and Java 7 installed

2. Checkout the repository to a local directory

3. Compile by calling `mvn clean install`

4. Use the generated JAR file, present at `target/pmarques_io-1.0.jar`.


License
=======

Apache 2.0 License.
