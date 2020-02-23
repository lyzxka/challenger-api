package io.renren.common.utils;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenEntityMySql {

	public static void main(String[] args) throws IOException {
		AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //执行完成后是否自动打开文件夹
        gc.setOpen(false);
        File directory = new File(".");
        gc.setOutputDir(directory.getCanonicalPath()+"\\src\\main\\java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
         gc.setMapperName("%sDao");
         gc.setXmlName("%sDao");
         gc.setServiceName("%sService");
         gc.setServiceImplName("%sServiceImpl");
//         gc.setControllerName("%s");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.contains("char") || fieldType.contains("text")) {
                    return DbColumnType.STRING;
                } else if (fieldType.contains("bigint")) {
                    return DbColumnType.LONG;
                } else if (fieldType.contains("int")) {
                    return DbColumnType.INTEGER;
                } else if (fieldType.contains("date") || fieldType.contains("time") || fieldType.contains("year")) {
                    return DbColumnType.DATE;
                } else if (fieldType.contains("text")) {
                    return DbColumnType.STRING;
                } else if (fieldType.contains("bit")) {
                    return DbColumnType.BOOLEAN;
                } else if (fieldType.contains("decimal")) {
                    return DbColumnType.BIG_DECIMAL;
                } else if (fieldType.contains("clob")) {
                    return DbColumnType.CLOB;
                } else if (fieldType.contains("blob")) {
                    return DbColumnType.BLOB;
                } else if (fieldType.contains("binary")) {
                    return DbColumnType.BYTE_ARRAY;
                } else if (fieldType.contains("float")) {
                    return DbColumnType.FLOAT;
                } else if (fieldType.contains("double")) {
                    return DbColumnType.DOUBLE;
                } else if (fieldType.contains("json") || fieldType.contains("enum")) {
                    return DbColumnType.STRING;
                }

                return super.processTypeConvert(globalConfig,fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("challenger");
        dsc.setPassword("asjildjoiasudon");
        dsc.setUrl("jdbc:mysql://39.105.34.103:3306/challenger?useUnicode=true&characterEncoding=utf8&useSSL=false&tinyInt1isBit=true");
        mpg.setDataSource(dsc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
    // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[] { ""});// 此处可以修改为您的表前缀

        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "ch_category","ch_group","ch_group_search","ch_knowledge","ch_match","ch_user"}); // 需要生成的表
//        strategy.setExclude(new String[]{"sys_user_role","sys_role_menu","sys_group_ur"}); // 排除生成的表
        // 自定义实体父类
//        strategy.setSuperEntityClass("com.rancii.base.DataEntity");
        // 自定义实体，公共字段
        //strategy.setSuperEntityColumns(new String[] { "id","create_date","create_by","update_date","update_by","remarks","del_flag" });
//        strategy.setSuperEntityColumns(new String[] { "id" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("io.renren.app");
        pc.setMapper("dao");
        pc.setController(null);
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig(){
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/vm/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                try {
                    return directory.getCanonicalPath()+"\\src\\main\\resources\\mapper\\" + tableInfo.getEntityName() + "Dao.xml";
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("/templates/vm/entity.java.vm");
        tc.setService("/templates/vm/service.java.vm");
        tc.setServiceImpl("/templates/vm/serviceImpl.java.vm");
//        tc.setController("/templates/vm/controller.java.vm");
        tc.setController(null);
        tc.setMapper("/templates/vm/mapper.java.vm");
        tc.setXml(null);
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

	}
}
