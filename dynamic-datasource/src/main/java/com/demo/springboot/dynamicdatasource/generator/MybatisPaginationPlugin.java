package com.demo.springboot.dynamicdatasource.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.List;


/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mybatis逆向生成：mybatis分页插件
 * @date 2021/2/10 9:55
 * @see
 */
public class MybatisPaginationPlugin extends PluginAdapter {

    public MybatisPaginationPlugin() {
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 多条件组合查询 example
     * @date 2021/2/10 10:06
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {
        this.addLimit(topLevelClass, introspectedTable, "limitStart");
        this.addLimit(topLevelClass, introspectedTable, "limitSize");
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description selectByExample 不带大字段； 分页语句拼接；
     * @date 2021/2/10 10:01
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitSize >= 0"));
        // mysql 的格式；
        isNotNullElement.addElement(new TextElement("limit #{limitStart} , #{limitSize}"));

        // pgsql 的格式；limit  A  offset  B
//        isNotNullElement.addElement(new TextElement("limit #{limitSize} offset #{limitStart}"));
        element.addElement(isNotNullElement);
        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description selectByExample 带大字段； 分页语句拼接；
     * @date 2021/2/10 10:01
     */
    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element,
                                                                  IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement
                .addAttribute(new Attribute("test", "limitStart != null and limitSize >= 0"));
        //        // mysql 的格式；
        isNotNullElement.addElement(new TextElement("limit #{limitStart} , #{limitSize}"));

        // pgsql 的格式；limit  A  offset  B
//        isNotNullElement.addElement(new TextElement("limit #{limitSize} offset #{limitStart}"));
        element.addElement(isNotNullElement);
        return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 添加限制条件
     * @date 2021/2/10 10:06
     */
    private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
                          String name) {
        CommentGenerator commentGenerator = this.context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(PrimitiveTypeWrapper.getIntegerInstance());
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), name));
        StringBuilder sb = new StringBuilder();
        sb.append("this.");
        sb.append(name);
        sb.append(" = ");
        sb.append(name);
        sb.append(";");
        method.addBodyLine(sb.toString());
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        Method getterMethod = AbstractJavaGenerator.getGetter(field);
        commentGenerator.addGeneralMethodComment(getterMethod, introspectedTable);
        topLevelClass.addMethod(getterMethod);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    /**
     * 生成mapper.xml,文件内容会被清空再写入
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        sqlMap.setMergeable(false);
        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }
}
