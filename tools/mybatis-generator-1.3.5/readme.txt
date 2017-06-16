修改generator.xml配置文件项
1. jdbcConnection
2. javaModelGenerator -- targetProject
3. sqlMapGenerator -- targetProject
4. javaClientGenerator -- targetProject

生成工具执行语句
java -jar mybatis-generator-core-1.3.5.jar -configfile generator.xml -overwrite