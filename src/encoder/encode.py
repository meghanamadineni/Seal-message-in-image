import tkinter as tk
from tkinter import filedialog 
import os

root = tk.Tk()
root.wm_title("Encode")
root.geometry("400x200")

top = tk.Frame(root)
top.place(relx=.5,rely=.5,anchor="center")
top.grid_columnconfigure(1,weight=1)

embedFrame = tk.Frame(top,width=370,height=20)
embedFrame.grid(row = 2,columnspan = 3,padx=5,pady=5,ipadx=5,ipady=5,sticky='we')

def browseCallBack():
	imagePath.delete(0, tk.END)
	message.delete(0, tk.END)
	imagePath.insert(0,filedialog.askopenfilename(defaultextension=".bmp", filetypes=(("bmp file", "*.bmp"),("All Files", "*.*") )))# show an "Open" dialog box and return the path to the selected file
	
def okCallBack():
	head, tail = os.path.split(imagePath.get())
	os.system("javac Embed.java")
	os.system('"'+"java -cp .;"+head+" Embed "+head+" "+tail+" "+'"'+message.get()+'"'+'"')
	
imageLabel = tk.Label(top, text="Image")
imageLabel.grid(column=0,row = 0,sticky='we',padx=5,pady=5)
imagePath = tk.Entry(top)
imagePath.grid(column=1,row = 0,sticky='we',padx=5,pady=5)
imagePath.focus_set()
Browse = tk.Button(top,text = "Browse", command = browseCallBack)
Browse.grid(row = 0,column=2,sticky='we',padx=5,pady=5)
msgLabel = tk.Label(top, text="Message")
msgLabel.grid(column=0,row = 1,sticky='we',padx=5,pady=5)
message = tk.Entry(top)
message.grid(column=1,row = 1,sticky='we',padx=5,pady=5)
message.focus_set()
Embed = tk.Button(embedFrame,text = "Encode", command = okCallBack)
Embed.config( height = 10, width = 5 )
Embed.place(relx=.5, rely=.5, anchor="center")

root.mainloop()
