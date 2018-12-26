package top.lllyl2012;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
 
public class Test_FileAlterationObserver
{
	
    public static void main(String[] args) throws Exception
    {
        Test_FileAlterationObserver test=new Test_FileAlterationObserver();
        test.test();
        test.test2();
    }
    public void test() throws Exception{
        final String filePath="E:\\Tomcat\\webapps\\manage\\WEB-INF\\classes\\templates";
        
        FileFilter filter=FileFilterUtils.and(new MyFileFilter());
        FileAlterationObserver filealtertionObserver=new FileAlterationObserver(filePath, filter);
        filealtertionObserver.addListener(new FileAlterationListenerAdaptor(){
 
            @Override
            public void onDirectoryCreate(File directory)
            {
                System.out.println("onDirectoryCreate");
                try {
                	String newPath="F:/backup/"+getNewPath();
                    File file = new File(newPath);
                    if(!file.exists()) {
                    	file.mkdirs();
                    }
					copyDir(filePath,newPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                super.onDirectoryCreate(directory);
            }
 
            @Override
            public void onDirectoryDelete(File directory)
            {
                // TODO Auto-generated method stub
                System.out.println("onDirectoryDelete");
                try {
                	String newPath="F:/backup/"+getNewPath();
                    File file5 = new File(newPath);
                    if(!file5.exists()) {
                    	file5.mkdirs();
                    }
					copyDir(filePath,newPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                super.onDirectoryDelete(directory);
            }
 
            @Override
            public void onFileChange(File file)
            {
                // TODO Auto-generated method stub
                System.out.println("onFileChange");
                try {
                	String newPath="F:/backup/"+getNewPath();
                    File file4 = new File(newPath);
                    if(!file4.exists()) {
                    	file4.mkdirs();
                    }
					copyDir(filePath,newPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                super.onFileChange(file);
            }
 
            @Override
            public void onFileCreate(File file)
            {
                System.out.println("onFileCreate"+getNewPath());
                try {
                	String newPath="F:/backup/"+ getNewPath();
                    File file3 = new File(newPath);
                    if(!file3.exists()) {
                    	file3.mkdirs();
                    }
					copyDir(filePath,newPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                super.onFileCreate(file);
            }
 
            @Override
            public void onFileDelete(File file)
            {
                // TODO Auto-generated method stub
                System.out.println("onFileDelete");
                try {
                	String newPath="F:/backup/"+getNewPath();
                    File file2 = new File(newPath);
                    if(!file2.exists()) {
                    	file2.mkdirs();
                    }
					copyDir(filePath,newPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
                super.onFileDelete(file);
            }
 
            @Override
            public void onStart(FileAlterationObserver observer)
            {
                // TODO Auto-generated method stub
//                System.out.println("onStart");
                super.onStart(observer);
            }
            
        });
        
        FileAlterationMonitor filealterationMonitor=new FileAlterationMonitor(1000);
        filealterationMonitor.addObserver(filealtertionObserver);
        filealterationMonitor.start();
    }
    
    public static void copyDir(String sourcePath, String newPath) throws IOException {
        File file = new File(sourcePath);
        String[] filePath = file.list();
        
        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }
        
        for (int i = 0; i < filePath.length; i++) {
            if ((new File(sourcePath + file.separator + filePath[i])).isDirectory()) {
                copyDir(sourcePath  + file.separator  + filePath[i],newPath  + file.separator + filePath[i]);
            }
            
            if (new File(sourcePath  + file.separator + filePath[i]).isFile()) {
                copyFile(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }
            
        }
    }
    
    public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);;

        byte[] buffer=new byte[2097152];
        int readByte = 0;
        while((readByte = in.read(buffer)) != -1){
            out.write(buffer, 0, readByte);
        }
    
        in.close();
        if(out!=null) {
        	out.close();
        }
    }
    
    public static String getNewPath() {
    	return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }

    public void test2() throws Exception{
        final String filePath="E:\\Tomcat\\webapps\\manage\\WEB-INF\\classes\\static";

        FileFilter filter=FileFilterUtils.and(new MyFileFilter());
        FileAlterationObserver filealtertionObserver=new FileAlterationObserver(filePath, filter);
        filealtertionObserver.addListener(new FileAlterationListenerAdaptor(){

            @Override
            public void onDirectoryCreate(File directory)
            {
                System.out.println("onDirectoryCreate");
                try {
                    String newPath="F:/backup/static/"+getNewPath();
                    File file = new File(newPath);
                    if(!file.exists()) {
                        file.mkdirs();
                    }
                    copyDir(filePath,newPath);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.onDirectoryCreate(directory);
            }

            @Override
            public void onDirectoryDelete(File directory)
            {
                // TODO Auto-generated method stub
                System.out.println("onDirectoryDelete");
                try {
                    String newPath="F:/backup/static/"+getNewPath();
                    File file5 = new File(newPath);
                    if(!file5.exists()) {
                        file5.mkdirs();
                    }
                    copyDir(filePath,newPath);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.onDirectoryDelete(directory);
            }

            @Override
            public void onFileChange(File file)
            {
                // TODO Auto-generated method stub
                System.out.println("onFileChange");
                try {
                    String newPath="F:/backup/static/"+getNewPath();
                    File file4 = new File(newPath);
                    if(!file4.exists()) {
                        file4.mkdirs();
                    }
                    copyDir(filePath,newPath);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.onFileChange(file);
            }

            @Override
            public void onFileCreate(File file)
            {
                System.out.println("onFileCreate"+getNewPath());
                try {
                    String newPath="F:/backup/static/"+ getNewPath();
                    File file3 = new File(newPath);
                    if(!file3.exists()) {
                        file3.mkdirs();
                    }
                    copyDir(filePath,newPath);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.onFileCreate(file);
            }

            @Override
            public void onFileDelete(File file)
            {
                // TODO Auto-generated method stub
                System.out.println("onFileDelete");
                try {
                    String newPath="F:/backup/static/"+getNewPath();
                    File file2 = new File(newPath);
                    if(!file2.exists()) {
                        file2.mkdirs();
                    }
                    copyDir(filePath,newPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.onFileDelete(file);
            }

            @Override
            public void onStart(FileAlterationObserver observer)
            {
                // TODO Auto-generated method stub
//                System.out.println("onStart");
                super.onStart(observer);
            }

        });

        FileAlterationMonitor filealterationMonitor=new FileAlterationMonitor(1000);
        filealterationMonitor.addObserver(filealtertionObserver);
        filealterationMonitor.start();
    }
    
}
class MyFileFilter implements IOFileFilter{
 
    public boolean accept(File file)
    {
        /*String extension=FilenameUtils.getExtension(file.getAbsolutePath());
        if(extension!=null&&extension.equals("txt")){
            return true;
        }
        return false;*/
        return true;
    }
 
    public boolean accept(File dir, String name)
    {
        return true;
    }
}
