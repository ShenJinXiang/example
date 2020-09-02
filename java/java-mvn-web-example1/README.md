
```
mvn install:install-file -DgroupId=com. -DartifactId=jar包的artifactId  -Dversion=jar包的版本号 -Dpackaging=jar -Dfile=jar包所在本地目录
mvn install:install-file -DgroupId=com.sj.shiro -DartifactId=jfinal-shiro -Dversion=2.0.0 -Dpackaging=jar -Dfile=lib/jfinal-shiro-2.0.0.jar

    <dependency>
      <groupId>com.sj.shiro</groupId>
      <artifactId>jfinal-shiro</artifactId>
      <version>2.0.0</version>
    </dependency>

```