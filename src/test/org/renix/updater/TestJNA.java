/*package org.renix.updater;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;


public class TestJNA {

    public static void main(String[] args) {
        // Read a string
        String productName =
                Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE,
                        "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion", "ProductName");
        System.out.printf("Product Name: %s\n", productName);

        // Read an int (& 0xFFFFFFFFL for large unsigned int)
        int timeout =
                Advapi32Util.registryGetIntValue(WinReg.HKEY_LOCAL_MACHINE,
                        "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Windows",
                        "ShutdownWarningDialogTimeout");
        System.out.printf("Shutdown Warning Dialog Timeout: %d (%d as unsigned long)\n", timeout,
                timeout & 0xFFFFFFFFL);

        // Create a key and write a string
        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\StackOverflow");
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\StackOverflow",
                "url", "http://stackoverflow.com/a/6287763/277307");

        // Delete a key
        Advapi32Util.registryDeleteKey(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\StackOverflow");
        
        
        //查看用户的CMD命令扩展是否被启用
        Integer enableExtensions = null;
        try {
            enableExtensions =
                    Advapi32Util.registryGetIntValue(WinReg.HKEY_CURRENT_USER,
                            "Software\\Microsoft\\Command Processor", "EnableExtensions1");
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(enableExtensions);
        Advapi32Util.registrySetIntValue(WinReg.HKEY_CURRENT_USER, "Software\\Microsoft\\Command Processor", "EnableExtensions", 1);
    }
}
*/