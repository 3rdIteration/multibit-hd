package org.multibit.hd.core.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;

import java.util.TimeZone;

/**
 * <p>Pattern to provide the following to logging framework:</p>
 * <ul>
 * <li>Log entry format</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class LogFormatter extends PatternLayout {

  public LogFormatter(LoggerContext context, TimeZone timeZone) {
    super();
    setOutputPatternAsHeader(false);
    getDefaultConverterMap().put("ex", PrefixedThrowableProxyConverter.class.getName());
    getDefaultConverterMap().put("xEx", PrefixedExtendedThrowableProxyConverter.class.getName());
    setPattern("[%d{ISO8601," + timeZone.getID() + "}] [%thread] %-5level %logger{16} - %msg %xEx%n");
    setContext(context);
  }

}
