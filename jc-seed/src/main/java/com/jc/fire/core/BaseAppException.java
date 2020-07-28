package com.jc.fire.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/7/27 17:06
 * @see com.company.project.core <br>
 * @since V8.1<br>
 */
public final class BaseAppException extends Exception implements Serializable {

    private static final long serialVersionUID = -27020729327620212L;

    public static final int INNER_ERROR = 1;

    public static final int BUSS_ERROR = 0;

    private int id;

    private String code;

    private String desc;

    private String localeMessage;

    private Date time;

    private int type;

    public BaseAppException() {
        super();
    }

    public BaseAppException(String code) {
        this(code, null, INNER_ERROR, null, null, null, null);
    }

    public BaseAppException(String code, String msg) {
        this(code, msg, INNER_ERROR, null, null, null, null);
    }

    public BaseAppException(String code, String msg, String arg0) {
        this(code, msg, INNER_ERROR, null, arg0, null, null);
    }

    public BaseAppException(String code, Throwable cause) {
        this(code, null, INNER_ERROR, cause, null, null, null);
    }

    public BaseAppException(String code, int errorType, Throwable cause) {
        this(code, null, errorType, cause, null, null, null);
    }

    public BaseAppException(String code, String msg, int errorType) {
        this(code, msg, errorType, null, null, null, null);
    }

    public BaseAppException(String code, String param1, Throwable cause) {
        this(code, null, INNER_ERROR, cause, param1, null, null);
    }

    public BaseAppException(String code, String param1, String param2, Throwable cause) {
        this(code, null, INNER_ERROR, cause, param1, param2, null);
    }

    /**
     * 为了可对异常信息进行参数替换，扩展了String arg0,String arg1,String arg2 三个参数
     *
     * @param errorCode String
     * @param message String
     * @param errorType int
     * @param cause Throwable
     * @param arg0 String
     * @param arg1 String
     * @param arg2 String
     */
    public BaseAppException(String errorCode, String message, int errorType, Throwable cause, String arg0, String arg1,
                            String arg2) {
        /** @todo* */
        super(message, cause);

        List<String> list = new ArrayList<String>(3);
        if (arg0 != null) {
            list.add(arg0);
        }
        if (arg1 != null) {
            list.add(arg1);
        }
        if (arg2 != null) {
            list.add(arg2);
        }
        String[] args = null;

        if (list.size() > 0) {
            args = new String[list.size()];
            int i = 0;
            for (String s : list) {
                args[i++] = s;
            }
        }

        this.code = errorCode;
        this.desc = message;
        BaseAppException beCause = new BaseAppException();
        while (cause != null) {
            if (cause instanceof BaseAppException) {
                beCause = (BaseAppException) cause;
                break;
            }
            cause = cause.getCause();
        }

        if (beCause == null) {
            this.type = errorType;
        }
        else {
            this.type = beCause.getType();
        }

        try {
            this.localeMessage = (code == null ? "" : code);

            if (args != null && args.length > 0) {
                this.localeMessage = this.replaceArgs(localeMessage, args);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 占位符替换
     *
     * @param s
     * @param args
     * @return
     */
    private String replaceArgs(String s, String args[]) {
        int i = 0;
        if (s != null && args != null && args.length > 0) {
            StringBuilder sb = new StringBuilder();
            Pattern p = Pattern.compile("\\{(.*?)\\}");
            Matcher m = p.matcher(s);
            while (m.find()) {
                s = s.replaceFirst("\\{(.*?)\\}", Matcher.quoteReplacement(args[i++]));
            }
            sb.append(s);
            return sb.toString();
        }

        return "";
    }

    /**
     * set Id
     *
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * set Code
     *
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * set Description
     *
     * @param desc String
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * set Time
     *
     * @param time Date
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * set type
     *
     * @param type int
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * get Id
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * get Code
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * get Description
     *
     * @return String
     */
    public String getDesc() {
        return desc;
    }

    /**
     * get Time
     *
     * @return Date
     */
    public Date getTime() {
        if (time == null) {
            time = new Date();
        }
        return time;
    }

    /**
     * get Type
     *
     * @return int
     */
    public int getType() {
        return type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("errorCode = [");
        sb.append(code);
        sb.append("] errorDesc = [");
        if (localeMessage != null) {
            sb.append(localeMessage);
        }
        sb.append("]");
        if (desc != null) {
            sb.append("  Description = [");
            sb.append(desc);
            sb.append("]");
        }

        return sb.toString();
    }

    public String toStringNonTrace() {
        StringBuilder sb = new StringBuilder();

        sb.append("errorCode = [");
        sb.append(code);
        sb.append("] errorDesc = [");
        if (localeMessage != null) {
            sb.append(localeMessage);
        }
        sb.append("]");
        if (desc != null) {
            sb.append("  Description = [");
            sb.append(desc);
            sb.append("]");
        }

        Throwable cause = getCause();
        if (cause != null) {
            while (true) {
                if (cause.getCause() != null) {
                    cause = cause.getCause();
                }
                else {
                    break;
                }
            }
        }
        if (cause != null) {
            sb.append(" cause = [");
            sb.append(cause.getClass().getName());
            sb.append(":");
            sb.append(cause.getMessage());
            sb.append("]");
        }
        return sb.toString();
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = getLocaleMessage();
            if (code != null) {
                message = new StringBuilder().append('[').append(code).append("] ").append(message).toString();
            }
        }
        else if (code != null) {
            message = new StringBuilder().append('[').append(code).append("] ").append(message).toString();
        }
        return message;
    }

    public String getLocaleMessage() {
        return localeMessage;
    }

    public void setLocaleMessage(String localeMessage) {
        this.localeMessage = localeMessage;
    }

    public String getDetailMessage() {
        StringBuilder content = new StringBuilder();
        if (code != null) {
            content.append('[').append(code).append("] ");
        }
        if (localeMessage != null) {
            content.append('[').append(localeMessage.trim()).append("] ");
        }
        String message = super.getMessage();
        if (message != null) {
            content.append('[').append(message.trim()).append("] ");
        }
        return content.toString();
    }
}
