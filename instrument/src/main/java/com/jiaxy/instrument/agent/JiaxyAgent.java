package com.jiaxy.instrument.agent;

import java.lang.instrument.Instrumentation;

/**
 * Description: <br/>
 *      java agent
 *
 *      @see <a href="http://dhruba.name/2010/02/07/creation-dynamic-loading-and-instrumentation-with-javaagents/">javaagents</>
 * <p/>
 * <br/>
 * @Date: 2016/11/25 12:07
 */
public class JiaxyAgent {

    private static Instrumentation instrumentation;


    /**
     * JVM hook to statically load the javaagent at startup.
     *
     * After the Java Virtual Machine (JVM) has initialized, the premain method
     *
     * will be called. Then the real application main method will be called.
     *
     * @param args
     * @param inst
     * @throws Exception
     */
    public static void premain(String args, Instrumentation inst) throws Exception {
        System.out.println(String.format("premain method invoked with args: %s and inst: %s", args, inst));
        instrumentation = inst;
        instrumentation.addTransformer(new JiaxyClassFileTransformer());
    }

    /**
     * JVM hook to dynamically load javaagent at runtime.
     *
     * The agent class may have an agentmain method for use when the agent is
     *
     * started after VM startup.
     *
     * @param args
     * @param inst
     * @throws Exception
     */
    public static void agentmain(String args, Instrumentation inst) throws Exception {
        System.out.println(String.format("agentmain method invoked with args: %s and inst: %s", args, inst));
        instrumentation = inst;
        instrumentation.addTransformer(new JiaxyClassFileTransformer());
    }




}
