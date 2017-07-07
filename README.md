# jgroups-tests

1. JGroupNode.java 
   This class can run as standalone program. 
   javac -cp jgroups-3.2.8.Final.jar JGroupNode.java
   java -cp jgroups-3.2.8.Final.jar JGroupNode
   
   Right now running as UDP protocol... By providing instantiating JChannel with "udp.xml"... Same can also be run as tcp by changing it with "tcp.xml". When we use tcp.xml, we should do IP property binding using below way
   
   java -cp jgroups-3.2.8.Final.jar -Djgroups.bind_addr=192.168.32.50 -Djgroups.tcpping.initial_hosts=192.168.32.50[7800] -Djava.net.preferIPv4Stack=true JGroupNode
   
