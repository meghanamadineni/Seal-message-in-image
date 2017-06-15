# SteganographyJPy
## Description 
Steganography is the science of hiding information. This project is about hiding the text within an image. The least significant bits of pixels of an image are modified to store the text. It is built with Java and Python GUI Programming (Tkinter). For the scope of this project, it only supports the BMP file format (bitmap image file). 

## Execution
For encoding, encode.py is run.<br/>
>python encode.py <br/>
Image location and the message to be embedded are given as inputs. The output image gets saved in the project root folder.<br/>
For decoding, decode.py is run.<br/>
>python decode.py <br/>
Image location from which message is to be extracted is given. On clicking Decode, output.txt is generated in the project root folder and opened for the user.<br/>
