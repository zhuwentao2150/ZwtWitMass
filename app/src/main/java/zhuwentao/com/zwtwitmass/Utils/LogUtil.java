package zhuwentao.com.zwtwitmass.utils;

import android.util.Log;
import android.util.SparseArray;

/**
 * 日志记录工具类
 * 
 * @author zhuwentao
 */
public class LogUtil {

    /**
     * 是否启用调试模式, 如果为false不记录任何日志
     */
    private static boolean ADB = false;
    
    /**
     * 设置Log打开标记，发布版本需要设置为false
     * @param tag true=打开日志输出
     */
    public static void setDebugOpen(boolean tag){
    	ADB = tag;
    }
    

    /**
     * 日志级别
     */
    private static int LOG_DEGREE = Log.VERBOSE;

    /**
     * 当前类记录日志的tag
     */
    private static final String TAG = "LogUtils";

    /**
     * 记录V级别日志 在记录V级别日志时调用, 如果日志配置为不记录日志或日志级别高于V, 不记录日志<br>
     * 默认tag为调用方的类名
     */
    public static void v(String... msg) {
        doV(null, msg);
    }
    
    /**
     * 记录V级别日志 在记录V级别日志时调用, 如果日志配置为不记录日志或日志级别高于V, 不记录日志
     */
    public static void vt(String tag, String... msg) {
        doV(tag, msg);
    }
    /**
     * 记录V级别日志 在记录V级别日志时调用, 如果日志配置为不记录日志或日志级别高于V, 不记录日志
     */
    private static void doV(String tag, String... msg) {
        if (ADB && LOG_DEGREE <= Log.VERBOSE) {
            String msgStr = combineLogMsg(tag, msg);
            
            String logTag = tag==null?combineLogTag():tag;
            
            Log.v(logTag, msgStr);
        }
    }

    /**
     * 记录D级别日志 在记录D级别日志时调用, 如果日志配置为不记录日志或日志级别高于D, 不记录日志<br>
     * 默认tag为调用方的类名
     */
    public static void d(String... msg) {
        doD(null, msg);
    }
    
    /**
     * 记录D级别日志 在记录D级别日志时调用, 如果日志配置为不记录日志或日志级别高于D, 不记录日志
     */
    public static void dt(String tag, String... msg) {
        doD(tag, msg);
    }
    
    private static void doD(String tag, String... msg) {
        if (ADB && LOG_DEGREE <= Log.DEBUG) {
            String msgStr = combineLogMsg(tag, msg);

            String logTag = tag==null?combineLogTag():tag;
            
            Log.d(logTag, msgStr);
        }
    }

    /**
     * 记录I级别日志 在记录I级别日志时调用, 如果日志配置为不记录日志或日志级别高于I, 不记录日志<br>
     * 默认tag为调用方的类名
     */
    public static void i(String... msg) {
        doI(null, msg);
    }
    
    /**
     * 记录I级别日志 在记录I级别日志时调用, 如果日志配置为不记录日志或日志级别高于I, 不记录日志
     */
    public static void it(String tag, String... msg) {
        doI(tag, msg);
    }
    
    /**
     * 执行I级别日志 在记录I级别日志时调用, 如果日志配置为不记录日志或日志级别高于I, 不记录日志
     */
    private static void doI(String tag, String... msg) {
        if (ADB && LOG_DEGREE <= Log.INFO) {
            String msgStr = combineLogMsg(tag, msg);

            String logTag = tag==null?combineLogTag():tag;
            
            Log.i(logTag, msgStr);
        }
    }

    /**
     * 记录W级别日志 在记录W级别日志时调用, 如果日志配置为不记录日志或日志级别高于W, 不记录日志<br>
     * 默认tag为调用方的类名
     */
    public static void w(String... msg) {
        doW(null, msg);
    }

    /**
     * 记录W级别日志 在记录W级别日志时调用, 如果日志配置为不记录日志或日志级别高于W, 不记录日志
     * 
     * @param tag 日志tag
     * @param msg 日志信息, 支持动态传参可以是一个或多个(避免日志信息的+操作过早的执行)
     */
    public static void wt(String tag, String... msg) {
        doW(tag, msg);
    }
    
    /**
     * 执行W级别日志 在记录W级别日志时调用, 如果日志配置为不记录日志或日志级别高于W, 不记录日志
     */
    private static void doW(String tag, String... msg) {
        if (ADB && LOG_DEGREE <= Log.WARN) {
            String msgStr = combineLogMsg(tag, msg);

            String logTag = tag==null?combineLogTag():tag;
            
            Log.w(logTag, msgStr);
        }
    }

    /**
     * 记录E级别日志 在记录E级别日志时调用, 如果日志配置为不记录日志或日志级别高于E, 不记录日志<br>
     * 默认tag为调用方的类名
     */
    public static void e(String... msg) {
        doE(null, null, msg);
    }
    
    /**
     * 记录E级别日志 在记录E级别日志时调用, 如果日志配置为不记录日志或日志级别高于E, 不记录日志
     */
    public static void et(String tag, String... msg) {
        doE(tag, null, msg);
    }

    /**
     * 记录E级别日志 在记录E级别日志时调用, 如果日志配置为不记录日志或日志级别高于E, 不记录日志<br>
     * 默认tag为调用方的类名
     */
    public static void e(Throwable tr, String... msg) {
        doE(null, tr, msg);
    }
    
    /**
     * 记录E级别日志 在记录E级别日志时调用, 如果日志配置为不记录日志或日志级别高于E, 不记录日志
     * 
     * @param tag 日志tag
     * @param tr 异常对象
     * @param msg 日志信息, 支持动态传参可以是一个或多个(避免日志信息的+操作过早的执行)
     */
    public static void et(String tag, Throwable tr, String... msg) {
        doE(tag, tr, msg);
    }
    
    private static void doE(String tag, Throwable tr, String... msg){
        if (ADB && LOG_DEGREE <= Log.ERROR) {
            String msgStr = combineLogMsg(tag, msg);

            String logTag = tag==null?combineLogTag():tag;
            
            Log.e(logTag, msgStr, tr);

        }
    }

    private static StackTraceElement getStackTraceElement(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //这个值是根据堆栈的调用结构而定的，如果改了调用的深度则这个值就需要重新算一下
        int index = 6;
        return stackTrace[index];
    }
    
    /**
     * 获取当前调用的类名为日志的tag
     */
    private static String combineLogTag() {
        StackTraceElement stack = getStackTraceElement();
        String className = stack.getFileName();
        if(className == null){
            className = stack.getClassName();
        }
        return className;
    }
    
    /**
     * 组装动态传参的字符串 将动态参数的字符串拼接成一个字符串
     * 
     * @param msg 动态参数
     * @return 拼接后的字符串
     */
    private static String combineLogMsg(String tag, String... msg) {
        
        StackTraceElement stack = getStackTraceElement();
        
        String methodName = stack.getMethodName();
        int lineNumber = stack.getLineNumber();

        StringBuilder sb = new StringBuilder();
        sb.append("[ (");
        
        String className = stack.getFileName();
        if(className == null){
            className = stack.getClassName();
        }
        sb.append(className).append(":");
        
        sb.append(lineNumber).append(")#").append(methodName).append(" ] ");

        if(msg != null){
            for (String s : msg) {
                sb.append(s);
            }
        } else {
            sb.append("Log with null Object");
        }
        
        return sb.toString();

    }

    /**
     * 日志级别与其对应的字符标签
     */
    private static SparseArray<String> degreeLabel = new SparseArray<String>();

    /**
     * 初始化日志级别与其对应的字符标签
     */
    static {
        degreeLabel.put(Log.VERBOSE, "V");
        degreeLabel.put(Log.DEBUG, "D");
        degreeLabel.put(Log.INFO, "I");
        degreeLabel.put(Log.WARN, "W");
        degreeLabel.put(Log.ERROR, "E");
    }

}

