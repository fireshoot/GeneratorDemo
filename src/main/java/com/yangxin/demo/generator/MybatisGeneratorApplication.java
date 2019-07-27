package com.yangxin.demo.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin
 * @time 2019/7/23  8:42
 */
public class MybatisGeneratorApplication {
    private static Logger logger = LoggerFactory.getLogger(MybatisGeneratorApplication.class);

    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        // 配置文件路径
        String xmlPath = "\\src\\main\\resources\\generatorConfig.xml";
        //String xmlPath = "\\src\\main\\resources\\generator2.xml";
        try {
            // System.getProperty("user.dir"),获取当前的工作路径，windows和linux里的路径格式不一致，这里用来转换
            String configFilePath = System.getProperty("user.dir").concat(
                    isNotWindows() ? xmlPath.replaceAll("\\\\", "/") : xmlPath);
            logger.info("加载配置文件的路径：" + configFilePath);
            boolean overwrite = true;
            File configFile = new File(configFilePath);
            // configFile.exists():测试此抽象路径名表示的文件或目录是否存在, isFile() :表示测试此抽象路径名表示的文件是否是一个标准文件。
            logger.info("" + configFile.exists());
            // 创建配置解析器
            ConfigurationParser cp = new ConfigurationParser(warnings);
            // 调用解析器创建配置对象()
            Configuration config = cp.parseConfiguration(configFile);
            // 创建一个ShellCallback对象，shellCallback接口是处理文件的创建和合并，默认是不支持文件合并的。
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            // 创建一个MyBatisGenerator对象。MyBatisGenerator类是真正用来执行生成动作的类
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            myBatisGenerator.generate(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        for (String wa : warnings) {
            logger.info("warning:" + wa);
        }
    }

    private static boolean isNotWindows() {
        // System.getProperty("os.name") 获取当前运行的系统名称
        return !System.getProperty("os.name").toLowerCase()
                .startsWith("windows");
    }

}
