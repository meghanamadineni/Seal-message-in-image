import tkinter as tk
from tkinter import filedialog 
import os

root = tk.Tk()
root.wm_title("Decode")
root.geometry("400x200")
	
top = tk.Frame(root)
top.place(relx=.5,rely=.5,anchor="center")
top.grid_columnconfigure(1,weight=1)

extractFrame = tk.Frame(top,width=370,height=20)
extractFrame.grid(row = 1,columnspan = 3,padx=5,pady=5,ipadx=5,ipady=5,sticky='we')

def browseCallBack():
	imagePath.delete(0, tk.END)
	imagePath.insert(0,filedialog.askopenfilename(defaultextension=".bmp", filetypes=(("bmp file", "*.bmp"),("All Files", "*.*") )))# show an "Open" dialog box and return the path to the selected file
	
	
def okCallBack():
	head, tail = os.path.split(imagePath.get())
	os.system("javac Extract.java")
	os.system('"'+"java -cp .;"+head+" Extract "+head+" "+tail+'"')
	os.system("output.txt")

imageLabel = tk.Label(top, text="Image")
imageLabel.grid(column=0,row = 0,sticky='we',padx=5,pady=5)
imagePath = tk.Entry(top)
imagePath.grid(column=1,row = 0,sticky='we',padx=5,pady=5)
imagePath.focus_set()
Browse = tk.Button(top,text = "Browse", command = browseCallBack)
Browse.grid(row = 0,column=2,sticky='we',padx=5,pady=5)

Extract = tk.Button(extractFrame,text = "Decode", command = okCallBack)
Extract.config( height = 10, width = 5 )
Extract.place(relx=.5, rely=.5, anchor="center")

root.mainloop()
