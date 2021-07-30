package org.github.hoorf.spring.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpringContextUtil implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        printTrack("123");
    }
    /**
     * 打印当前线程堆栈信息
     * @param prefix
     */
    public static void printTrack(String prefix){
        StackTraceElement[] st = Thread.currentThread().getStackTrace();

        if(null==st){
            log.info("invalid stack");
            return;
        }

        StringBuffer sbf =new StringBuffer();

        for(StackTraceElement e:st){
            if(sbf.length()>0){
                sbf.append(" <- ");
                sbf.append(System.getProperty("line.separator"));
            }

            sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}"
                    ,e.getClassName()
                    ,e.getMethodName()
                    ,e.getLineNumber()));
        }

        log.info(prefix
                + "\n************************************************************\n"
                + sbf.toString()
                + "\n************************************************************");
    }

}
