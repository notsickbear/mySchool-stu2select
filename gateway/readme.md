# Registry service

## 1.Service dependency

![](./gateway.png)

## 2. Build registry service docker image

### 2.1 Modify maven.xml to adapt to your environment

default maven-settings.xml adapt rainbond
```xml
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <mirrors>
    <mirror>
      <id>rainbond-mirror</id>
      <mirrorOf>*</mirrorOf>
      <name>rainbond-mirror</name>
      <url>http://maven.goodrain.me/</url>
    </mirror>
</mirrors>

</settings>

```

### 2.2 Build image

```bash
docker build -t registry .
```

## 3. ENV

| Name |Default Value |Info|Required|
|---------|-------------|-------|--------|
| PORT |8761| Server Port| NO |
| CONFIG_PASS| NULL | Config Service Password|Yes|

## 3. Local test

```bash
docker run -it -e CONFIG_PASS=pass4you gateway
```

