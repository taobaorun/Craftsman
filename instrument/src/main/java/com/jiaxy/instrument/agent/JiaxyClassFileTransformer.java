package com.jiaxy.instrument.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Description: <br/>
 * <p/>
 * <br/>
 * @Date: 2016/11/25 13:16
 */
public class JiaxyClassFileTransformer implements ClassFileTransformer{

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("class file transformer invoked for class:"+className);
        return classfileBuffer;
    }
}
