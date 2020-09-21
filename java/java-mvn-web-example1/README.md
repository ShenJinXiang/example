
```
mvn install:install-file -DgroupId=com. -DartifactId=jar包的artifactId  -Dversion=jar包的版本号 -Dpackaging=jar -Dfile=jar包所在本地目录
mvn install:install-file -DgroupId=com.sj.shiro -DartifactId=jfinal-shiro -Dversion=2.0.0 -Dpackaging=jar -Dfile=lib/jfinal-shiro-2.0.0.jar
mvn install:install-file -DgroupId=com.sj.rxtx.comm -DartifactId=rxtxcomm -Dversion=2.2.0 -Dpackaging=jar -Dfile=D:\\soft\\java\\java1.8\\jre\\lib\\ext\\RXTXcomm.jar

    <dependency>
      <groupId>com.sj.shiro</groupId>
      <artifactId>jfinal-shiro</artifactId>
      <version>2.0.0</version>
    </dependency>

```