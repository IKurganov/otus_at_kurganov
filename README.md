# otus_at_kurganov
Курс автоматизация тестирования java otus

---> 1 ДЗ будет в ветке master


Ниже, надеюсь, не актуальная инфа по логированию: 

1) Логирование - настройки игнорируются в .gitignore, на данный момент по заданию не прокидываю в Гит всё лишнее.
Но оно должно работать, для этого: 
   а) Создать в target\classes файл log4j2.xml
   б) Указать в нём содержимое:
   `<Configuration monitorInterval="30">
   <Appenders>
   <Console name="Console" target="SYSTEM_OUT">
   <PatternLayout pattern="%highlight{[%d{HH:mm:ss.SSS}] | %-5p | %-32.42t | %-32.42c{1} | %m%n}{FATAL=red blink, ERROR=red blink, WARN=yellow bold, INFO=white, DEBUG=green bold, TRACE=blue}"/>
   </Console>
   <File name="MyFile" fileName="logs.log">
   <PatternLayout>
   <Pattern>%d %p %c{1.} [%t] %m%n</Pattern>
   </PatternLayout>
   </File>
   </Appenders>
   <Loggers>
   <Root level="info">
   <AppenderRef ref="Console"/>
   <AppenderRef ref="MyFile"/>
   </Root>
   </Loggers>
   </Configuration>`
   в) ???????
   г) PROFIT!!!!!
