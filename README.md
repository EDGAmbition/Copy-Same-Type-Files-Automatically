# Copy-Same-Type-Files-Automatically

可以拷贝指定目录（及子目录下所有）下，指定扩展名的文件
## myIOUtil
### static void copyOneFile(File from,File to) 
* from：要拷贝的``文件``
*   to：要拷贝到的位置，是一个``文件夹``

### static void copyFiles(File dir,File to,String[] extension)
*       dir：在dir``文件夹``下进行搜索
*        to：拷贝到to``文件夹``下
* extension：想要拷贝文件的扩展名，例如 .java .pdf
