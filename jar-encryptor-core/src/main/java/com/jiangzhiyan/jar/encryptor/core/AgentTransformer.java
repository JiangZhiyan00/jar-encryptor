package com.jiangzhiyan.jar.encryptor.core;


import com.jiangzhiyan.jar.encryptor.core.util.JarUtils;
import com.jiangzhiyan.jar.encryptor.core.util.StrUtils;
import lombok.RequiredArgsConstructor;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;


/**
 * AgentTransformer
 * jvm加载class时回调
 */
@RequiredArgsConstructor
public class AgentTransformer implements ClassFileTransformer {
    //密码
    private final char[] pwd;

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain domain, byte[] classBuffer) {
        if (className == null || domain == null || loader == null) {
            return classBuffer;
        }

        //获取类所在的项目运行路径
        String projectPath = domain.getCodeSource().getLocation().getPath();
        projectPath = JarUtils.getRootPath(projectPath);
        if (StrUtils.isEmpty(projectPath)) {
            return classBuffer;
        }

        className = className.replace("/", ".").replace("\\", ".");

        byte[] bytes = JarDecryptor.getInstance().doDecrypt(projectPath, className, this.pwd);
        //CAFEBABE,表示解密成功
        if (bytes != null && bytes[0] == -54 && bytes[1] == -2 && bytes[2] == -70 && bytes[3] == -66) {
            return bytes;
        }
        return classBuffer;

    }
}
