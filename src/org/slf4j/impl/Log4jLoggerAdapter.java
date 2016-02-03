/**
 * Copyright (c) 2004-2011 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.slf4j.impl;

import java.io.Serializable;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A wrapper over {@link org.apache.log4j.Logger org.apache.log4j.Logger} in
 * conforming to the {@link Logger} interface.
 * 
 * <p>
 * Note that the logging levels mentioned in this class refer to those defined
 * in the <a
 * href="http://logging.apache.org/log4j/docs/api/org/apache/log4j/Level.html">
 * <code>org.apache.log4j.Level</code></a> class.
 * 
 * <p>
 * The TRACE level was introduced in log4j version 1.2.12. In order to avoid
 * crashing the host application, in the case the log4j version in use predates
 * 1.2.12, the TRACE level will be mapped as DEBUG. See also <a
 * href="http://jira.qos.ch/browse/SLF4J-59">SLF4J-59</a>.
 * 
 * @author Ceki G&uuml;lc&uuml;
 */
public final class Log4jLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger, Serializable {

    private static final long serialVersionUID = 6182834493563598289L;

    final transient org.apache.log4j.Logger logger;

    /**
     * Following the pattern discussed in pages 162 through 168 of "The complete
     * log4j manual".
     */
    final static String FQCN = Log4jLoggerAdapter.class.getName();

    // Does the log4j version in use recognize the TRACE level?
    // The trace level was introduced in log4j 1.2.12.
    final boolean traceCapable;

    // WARN: Log4jLoggerAdapter constructor should have only package access so
    // that
    // only Log4jLoggerFactory be able to create one.
    Log4jLoggerAdapter(org.apache.log4j.Logger logger) {
        this.logger = logger;
        this.name = logger.getName();
        traceCapable = isTraceCapable();
    }

    private boolean isTraceCapable() {
    	return false;
    }

    /**
     * Is this logger instance enabled for level ERROR?
     * 
     * @return True if this Logger is enabled for level ERROR, false otherwise.
     */
    public boolean isErrorEnabled() {
        return logger.isEnabledFor(Level.ERROR);
    }

    /**
     * Log a message object at the ERROR level.
     * 
     * @param msg
     *          - the message object to be logged
     */
    public void error(String msg) {
        logger.log(FQCN, Level.ERROR, msg, null);
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     * 
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     * 
     * @param format
     *          the format string
     * @param arg
     *          the argument
     */
    public void error(String format, Object arg) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * arguments.
     * 
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     * 
     * @param format
     *          the format string
     * @param arg1
     *          the first argument
     * @param arg2
     *          the second argument
     */
    public void error(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at level ERROR according to the specified format and
     * arguments.
     * 
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     * 
     * @param format
     *          the format string
     * @param argArray
     *          an array of arguments
     */
    public void error(String format, Object... argArray) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     * 
     * @param msg
     *          the message accompanying the exception
     * @param t
     *          the exception (throwable) to log
     */
    public void error(String msg, Throwable t) {
        logger.log(FQCN, Level.ERROR, msg, t);
    }

    public void log(Marker marker, String callerFQCN, int level, String msg, Object[] argArray, Throwable t) {
        Level log4jLevel=Level.ERROR;
        logger.log(callerFQCN, log4jLevel, msg, t);
    }

    @Override
    public boolean isTraceEnabled() {
    	return false;
    }
    
	@Override
	public void trace(String msg)
	{}

	@Override
	public void trace(String format, Object arg)
	{}

	@Override
	public void trace(String format, Object arg1, Object arg2)
	{}

	@Override
	public void trace(String format, Object... arguments)
	{}

	@Override
	public void trace(String msg, Throwable t)
	{}

	@Override
	public boolean isDebugEnabled()
	{
		return false;
	}

	@Override
	public void debug(String msg)
	{}

	@Override
	public void debug(String format, Object arg)
	{}

	@Override
	public void debug(String format, Object arg1, Object arg2)
	{}

	@Override
	public void debug(String format, Object... arguments)
	{}

	@Override
	public void debug(String msg, Throwable t)
	{}

	@Override
	public boolean isInfoEnabled()
	{
		return false;
	}

	@Override
	public void info(String msg)
	{}

	@Override
	public void info(String format, Object arg)
	{}

	@Override
	public void info(String format, Object arg1, Object arg2)
	{}

	@Override
	public void info(String format, Object... arguments)
	{}

	@Override
	public void info(String msg, Throwable t)
	{}

	@Override
	public boolean isWarnEnabled()
	{
		return false;
	}

	@Override
	public void warn(String msg)
	{}

	@Override
	public void warn(String format, Object arg)
	{}

	@Override
	public void warn(String format, Object... arguments)
	{}

	@Override
	public void warn(String format, Object arg1, Object arg2)
	{}

	@Override
	public void warn(String msg, Throwable t)
	{}

}